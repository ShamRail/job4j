package additionalquestions;

import java.util.concurrent.CountDownLatch;

public class DemoThread implements Runnable {

    private CountDownLatch countDownLatch;
    private final Object obj1;
    private final Object obj2;

    public DemoThread(CountDownLatch countDownLatch, Object obj1, Object obj2) {
        this.countDownLatch = countDownLatch;
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    @Override
    public void run() {
        synchronized (obj1) {
            countDownLatch.countDown();
            try {
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (obj2) {
                System.out.println("Thread finished");
            }
        }
    }
}
