package additionalquestions;

import java.util.concurrent.CountDownLatch;

public class Deadlock {

    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();
        CountDownLatch latch = new CountDownLatch(2);
        Thread thread1 = new Thread(new DemoThread(latch, obj1, obj2));
        Thread thread2 = new Thread(new DemoThread(latch, obj1, obj2));
        thread1.start();
        thread2.start();
    }
}