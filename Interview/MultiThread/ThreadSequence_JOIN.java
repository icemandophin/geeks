/*
You have thread T1, T2 and T3, how will you ensure that thread T2 run after T1 and
thread T3 run after T2?
*/
public class Demo {
    public static void main(String [] args) {
        // create 3 new threads
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("Thread 1");
            }

        });
        Thread t2 = new Thread(new Runnable () {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("Thread 2");
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("Thread 3");
            }

        });
        // start 3 threads in parallel
        t1.start();
        try {
            // main thread call join to stop running
            // and wait for the t1 thread to finish
            // t2 thread is running in parallel
            t1.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        t3.start();


    }
}
