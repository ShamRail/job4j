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

    @Test
    public void maxTest() {
        PriorityStack<Integer> stack = new PriorityStack<>(Integer::compareTo);
        stack.offer(5);
        stack.offer(4);
        stack.offer(3);
        stack.offer(2);
        stack.offer(1);
        Assert.assertThat(stack.max(), Is.is(5));
    }

    @Test
    public void minTest() {
        PriorityStack<Integer> stack = new PriorityStack<>(Integer::compareTo);
        stack.offer(7);
        stack.offer(2);
        stack.offer(1);
        stack.offer(4);
        stack.offer(5);
        Assert.assertThat(stack.min(), Is.is(1));
    }
}