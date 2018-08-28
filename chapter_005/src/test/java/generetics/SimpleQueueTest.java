package generetics;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SimpleQueueTest {

    SimpleQueue<Integer> simpleQueue = new SimpleQueue<>();

    @Before
    public void setup() {
        simpleQueue.push(1);
        simpleQueue.push(2);
        simpleQueue.push(3);
    }

    @Test
    public void whenTestPollMethod() {
        Assert.assertThat(simpleQueue.poll(), Is.is(1));
        Assert.assertThat(simpleQueue.poll(), Is.is(2));
        Assert.assertThat(simpleQueue.poll(), Is.is(3));
    }

    @Test
    public void whenQueueIsEmptyMustReturnNull() {
        simpleQueue = new SimpleQueue<>();
        Assert.assertThat(simpleQueue.poll(), Is.is((Integer) null));
    }

}