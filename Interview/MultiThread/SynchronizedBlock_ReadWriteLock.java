/*
implement a high performance Concurrent HashMap in cache which allows multiple reader
but single writer to keep the integrity
*/
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentHashMap {
    private final ReentrantReadWriteLock fLock = new ReentrantReadWriteLock();
    private final Lock readLock = fLock.readLock();
    private final Lock writeLock = fLock.writeLock();
    private final HashMap<String, String> dataMap = new HashMap<String, String>();

    public String get(String key) {
        String result = new String();
        readLock.lock();
        try {
            result = dataMap.get(key);
        }
        catch (Exception e) {

        }
        finally {
            readLock.unlock();
        }
        return result;
    }

    public void set(String key, String value) {
        writeLock.lock();
        try {
            dataMap.put(key, value);
        }
        catch (Exception e) {

        }
        finally {
            writeLock.unlock();
        }
    }
}

/*
Local Variable: Each thread will have its own stack and local variable is stored
in the stack, so it is thread safe.
Local Object: Local Object’s reference is thread safe, however, the local object
itself is not necessarily the same. As long as the reference is not shared between
the threads, then it is thread safe. Otherwise, it is not.
Object Members: Are not thread safe, because it is stored in the shared heap.

To achieve thread safety:
1. use immutable objects: Although the immutable object itself is thread safe,
its reference may be not.
2. Use synchronized block: all the synchronized block are synchronized on the object.
If one of the synchronized block is being executed by one thread, the other thread
must wait until the modification is done to enter a synchronized block on the same object.
*/
