package queues;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class PriorityQueueTest {

    @Test
    public void peekOfferTest() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Integer::compareTo);
        priorityQueue.offer(1);
        priorityQueue.offer(2);
        priorityQueue.offer(3);
        Assert.assertThat(priorityQueue.peek(), Is.is(3));
    }

    @Test
    public void pollTest() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Integer::compareTo);
        priorityQueue.offer(1);
        priorityQueue.offer(2);
        priorityQueue.offer(3);
        priorityQueue.offer(4);
        priorityQueue.offer(5);
        Assert.assertThat(priorityQueue.poll(), Is.is(5));
        Assert.assertThat(priorityQueue.poll(), Is.is(4));
        Assert.assertThat(priorityQueue.poll(), Is.is(3));
        Assert.assertThat(priorityQueue.poll(), Is.is(2));
        Assert.assertThat(priorityQueue.poll(), Is.is(1));
        Assert.assertThat(priorityQueue.poll(), Is.is((Integer) null));

    }

}