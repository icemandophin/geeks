/*
reference: https://zhuanlan.zhihu.com/p/21103657
           https://github.com/bbeck/token-bucket
A token bucket implementation that is of a leaky bucket in the sense that
it has a finite capacity and any added tokens that would exceed this capacity
will "overflow" out of the bucket and are lost forever.
*/
package org.isomorphism.util;
import java.util.concurrent.TimeUnit;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.base.Ticker;
/**
 * A token bucket implementation that is of a leaky bucket in the sense that it has a finite capacity and any added
 * tokens that would exceed this capacity will "overflow" out of the bucket and are lost forever.
 * <p/>
 * In this implementation the rules for refilling the bucket are encapsulated in a provided {@code RefillStrategy}
 * instance.  Prior to attempting to consume any tokens the refill strategy will be consulted to see how many tokens
 * should be added to the bucket.
 * <p/>
 * In addition in this implementation the method of yielding CPU control is encapsulated in the provided
 * {@code SleepStrategy} instance.  For high performance applications where tokens are being refilled incredibly quickly
 * and an accurate bucket implementation is required, it may be useful to never yield control of the CPU and to instead
 * busy wait.  This strategy allows the caller to make this decision for themselves instead of the library forcing a
 * decision.
 */
class TokenBucketImpl implements TokenBucket
{
  private final long capacity;
  private final RefillStrategy refillStrategy;
  private final SleepStrategy sleepStrategy;
  private long size;

  TokenBucketImpl(long capacity, long initialTokens, RefillStrategy refillStrategy, SleepStrategy sleepStrategy)
  {
    checkArgument(capacity > 0);
    checkArgument(initialTokens <= capacity);

    this.capacity = capacity;
    this.refillStrategy = checkNotNull(refillStrategy);
    this.sleepStrategy = checkNotNull(sleepStrategy);
    this.size = initialTokens;
  }

  /**
   * Returns the capacity of this token bucket.  This is the maximum number of tokens that the bucket can hold at
   * any one time.
   *
   * @return The capacity of the bucket.
   */
  @Override
  public long getCapacity()
  {
    return capacity;
  }

  /**
   * Returns the current number of tokens in the bucket.  If the bucket is empty then this method will return 0.
   *
   * @return The current number of tokens in the bucket.
   */
  @Override
  public synchronized long getNumTokens()
  {
    // Give the refill strategy a chance to add tokens if it needs to so that we have an accurate
    // count.
    refill(refillStrategy.refill());

    return size;
  }

  /**
  * Returns the amount of time in the specified time unit until the next group of tokens can be added to the token
  * bucket.
  *
  * @see org.isomorphism.util.TokenBucket.RefillStrategy#getDurationUntilNextRefill(java.util.concurrent.TimeUnit)
  * @param unit The time unit to express the return value in.
  * @return The amount of time until the next group of tokens can be added to the token bucket.
  */
  @Override
  public long getDurationUntilNextRefill(TimeUnit unit) throws UnsupportedOperationException
  {
    return refillStrategy.getDurationUntilNextRefill(unit);
  }

  /**
   * Attempt to consume a single token from the bucket.  If it was consumed then {@code true} is returned, otherwise
   * {@code false} is returned.
   *
   * @return {@code true} if a token was consumed, {@code false} otherwise.
   */
  public boolean tryConsume()
  {
    return tryConsume(1);
  }

  /**
   * Attempt to consume a specified number of tokens from the bucket.  If the tokens were consumed then {@code true}
   * is returned, otherwise {@code false} is returned.
   *
   * @param numTokens The number of tokens to consume from the bucket, must be a positive number.
   * @return {@code true} if the tokens were consumed, {@code false} otherwise.
   */
  public synchronized boolean tryConsume(long numTokens)
  {
    checkArgument(numTokens > 0, "Number of tokens to consume must be positive");
    checkArgument(numTokens <= capacity, "Number of tokens to consume must be less than the capacity of the bucket.");

    refill(refillStrategy.refill());

    // Now try to consume some tokens
    if (numTokens <= size) {
      size -= numTokens;
      return true;
    }

    return false;
  }

  /**
   * Consume a single token from the bucket.  If no token is currently available then this method will block until a
   * token becomes available.
   */
  public void consume()
  {
    consume(1);
  }

  /**
   * Consumes multiple tokens from the bucket.  If enough tokens are not currently available then this method will block
   * until
   *
   * @param numTokens The number of tokens to consume from teh bucket, must be a positive number.
   */
  public void consume(long numTokens)
  {
    while (true) {
      if (tryConsume(numTokens)) {
        break;
      }

      sleepStrategy.sleep();
    }
  }

  /**
   * Refills the bucket with the specified number of tokens.  If the bucket is currently full or near capacity then
   * fewer than {@code numTokens} may be added.
   *
   * @param numTokens The number of tokens to add to the bucket.
   */
  public synchronized void refill(long numTokens)
  {
    long newTokens = Math.min(capacity, Math.max(0, numTokens));
    size = Math.max(0, Math.min(size + newTokens, capacity));
  }
}

/**
 * A token bucket refill strategy that will provide N tokens for a token bucket to consume every T units of time.
 * The tokens are refilled in bursts rather than at a fixed rate.  This refill strategy will never allow more than
 * N tokens to be consumed during a window of time T.
 */
public class FixedIntervalRefillStrategy implements TokenBucketImpl.RefillStrategy
{
  private final Ticker ticker;
  private final long numTokensPerPeriod;
  private final long periodDurationInNanos;
  private long lastRefillTime;
  private long nextRefillTime;

  /**
   * Create a FixedIntervalRefillStrategy.
   *
   * @param ticker             A ticker to use to measure time.
   * @param numTokensPerPeriod The number of tokens to add to the bucket every period.
   * @param period             How often to refill the bucket.
   * @param unit               Unit for period.
   */
  public FixedIntervalRefillStrategy(Ticker ticker, long numTokensPerPeriod, long period, TimeUnit unit)
  {
    this.ticker = ticker;
    this.numTokensPerPeriod = numTokensPerPeriod;
    this.periodDurationInNanos = unit.toNanos(period);
    this.lastRefillTime = -periodDurationInNanos;
    this.nextRefillTime = -periodDurationInNanos;
  }

  @Override
  public synchronized long refill()
  {
    long now = ticker.read();
    if (now < nextRefillTime) {
      return 0;
    }

    // We now know that we need to refill the bucket with some tokens, the question is how many.  We need to count how
    // many periods worth of tokens we've missed.
    long numPeriods = Math.max(0, (now - lastRefillTime) / periodDurationInNanos);

    // Move the last refill time forward by this many periods.
    lastRefillTime += numPeriods * periodDurationInNanos;

    // ...and we'll refill again one period after the last time we refilled.
    nextRefillTime = lastRefillTime + periodDurationInNanos;

    return numPeriods * numTokensPerPeriod;
  }

  @Override
  public long getDurationUntilNextRefill(TimeUnit unit)
  {
    long now = ticker.read();
    return unit.convert(Math.max(0, nextRefillTime - now), TimeUnit.NANOSECONDS);
  }
}
