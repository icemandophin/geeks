/*
callback approach
*/
interface Callback {
    void callback(); // would be in any signature
}

class MyThread implements Runnable {

    Callback c;

    public MyThread(Callback c) {
        this.c = c;
    }

    public void run() {
        // some work
        this.c.callback(); // callback
    }
}

/*
recommended: no limitation for inheritance
*/
public class RunnableExample implements Runnable {
    @Override
    public void run() {
        // custom logic
    }

    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());

        System.out.println("Creating Runnable...");
        Runnable runnable = new RunnableExample();

        System.out.println("Creating Thread...");
        Thread thread = new Thread(runnable);

        System.out.println("Starting Thread...");
        thread.start();
    }
}

public class CustomThread extends Thread {
    @Override
    public void run() {
        // run() method contains the code that is executed by the thread.
    }

    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());

        System.out.println("Creating thread...");
        Thread thread = new CustomThread();

        System.out.println("Starting thread...");
        thread.start();
    }
}
