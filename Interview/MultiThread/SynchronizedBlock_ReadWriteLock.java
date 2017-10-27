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
    private final HashMap<Integer, String> dataMap = new HashMap<>();

    public String get(int key) {
        String result = new String();
        // wait until hash map available
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

    public void set(int key, String value) {
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
	public static void main(String[] args) {
		ConcurrentHashMap test = new ConcurrentHashMap();
        Thread wp = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                        try {
                			test.set(i, Integer.toString(i));
                			System.out.println("add: " + Integer.toString(i));
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                }
            }

        });
        Thread rp = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 100; j++) {
                        try {
                        	String buf = test.get(j);
                            System.out.println("get: " + buf);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                }
            }

        });
        wp.start();
        rp.start();
	}

}
