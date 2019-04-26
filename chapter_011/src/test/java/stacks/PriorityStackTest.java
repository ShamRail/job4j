package stacks;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PriorityStackTest {

    @Test
    public void offerTest() {
        PriorityStack<Integer> stack = new PriorityStack<>(Integer::compareTo);
        stack.offer(1);
        stack.offer(2);
        Assert.assertThat(stack.peek(), Is.is(2));
    }

    @Test
    public void peekTest() {
        PriorityStack<Integer> stack = new PriorityStack<>(Integer::compareTo);
        stack.offer(1);
        Assert.assertThat(stack.peek(), Is.is(1));
    }

    @Test
    public void pollTest() {
        PriorityStack<Integer> stack = new PriorityStack<>(Integer::compareTo);
        stack.offer(1);
        Assert.assertThat(stack.poll(), Is.is(1));
    }
}