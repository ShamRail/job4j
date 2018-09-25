package waitnotify;

import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(10);
        Thread thread1 = new Thread(new Producer(simpleBlockingQueue));
        Thread thread2 = new Thread(new Consumer(simpleBlockingQueue));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}