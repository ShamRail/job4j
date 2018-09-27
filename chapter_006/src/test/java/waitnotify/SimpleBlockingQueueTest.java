package waitnotify;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;


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

    @Test
    public void whenTwoProducerAndOneConsumer() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producerFirst = new Thread(
                () -> IntStream.range(0, 5).forEach(
                        queue::offer
                )
        );
        Thread producerSecond = new Thread(
                () -> IntStream.range(0, 5).forEach(
                        queue::offer
                )
        );
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        producerFirst.start();
        producerFirst.join();
        producerSecond.start();
        producerSecond.join();
        consumer.start();
        consumer.interrupt();
        consumer.join();
        Assert.assertThat(buffer, Is.is(Arrays.asList(0, 1, 2, 3, 4, 0, 1, 2, 3, 4)));
    }
}