import java.util.*;

/*
 * similar to # 362
 * two buckets approach: one to save timestamp and one to save associated request count
 * notice bucket size is decided by resolution of timestamp like 1s / 1ms = 1000
 */
class RateLimiter {
	// store req timestamp that comes in during last 1s
	private static int[] ts;
	private static int[] cnt;
	// record request quota per sec
	private static int size;

	public RateLimiter(int n) {
		// 1 s = 1000 ms which is min unit for timestamp
		// hence create 1000 bucket
		ts = new int[1000];
		cnt = new int[1000];
		size = n;
	}
    // given incoming request's timestamp, check if it should be accepted/rejected
    public boolean shouldPass(int timestamp) {
    	// check which bucket cur request should belong to
    	int i = timestamp % 1000;
    	int sum = 0;

    	if (ts[i] == timestamp) {
    		// cur request comes together in time with last req in this bucket
    		cnt[i]++;
    	} else {
    		// cur request comes from more than 1s ago
    		// bucket of this ms is ready for new req
    		// reset bucket with new time and count
    		ts[i] = timestamp;
    		cnt[i] = 1;
    	}
    	// traverse each bucket and check if its req arrive within last sec
    	for (int j = 0; j < 1000; ++j) {
    		// compare req in bucket with cur time
    		if (timestamp - ts[j] < 1000) {
    			sum += cnt[j];
    		}
    	}

    	if (sum > size) {
    		return false;
    	} else {
    		return true;
    	}
    }
}

public class Solution {
    public static void main(String[] args) {
    	RateLimiter one = new RateLimiter(5);

        System.out.println(one.shouldPass(1200));
        System.out.println(one.shouldPass(1300));
        System.out.println(one.shouldPass(1500));
        System.out.println(one.shouldPass(1700));
        System.out.println(one.shouldPass(1800));
        System.out.println(one.shouldPass(2100));
        System.out.println(one.shouldPass(2400));
    }
}
