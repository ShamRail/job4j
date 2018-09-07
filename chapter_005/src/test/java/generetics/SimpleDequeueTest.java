package generetics;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class SimpleDequeueTest {

    @Test(expected = ConcurrentModificationException.class)
    public void whenOccurModificationWhenIterateMustBeException() {
        SimpleDequeue<Integer> simpleDequeue = new SimpleDequeue<>();
        simpleDequeue.insertFirst(1);
        Iterator<Integer> integerIterator = simpleDequeue.iterator();
        simpleDequeue.insertFirst(1);
        integerIterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDequeueIsEmptyThenBeException() {
        SimpleDequeue<Integer> simpleDequeue = new SimpleDequeue<>();
        Iterator<Integer> integerIterator = simpleDequeue.iterator();
        integerIterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextElementIsNotExistThenBeException() {
        SimpleDequeue<Integer> simpleDequeue = new SimpleDequeue<>();
        simpleDequeue.insertFirst(1);
        Iterator<Integer> integerIterator = simpleDequeue.iterator();
        integerIterator.next();
        integerIterator.next();
    }

    @Test
    public void whenIterateWithoutModificationThenReturnValues() {
        SimpleDequeue<Integer> simpleDequeue = new SimpleDequeue<>();

        simpleDequeue.insertFirst(1);
        simpleDequeue.insertFirst(2);
        simpleDequeue.insertLast(3);
        simpleDequeue.insertLast(4);

        Iterator<Integer> integerIterator = simpleDequeue.iterator();

        Assert.assertThat(integerIterator.hasNext(), Is.is(true));
        Assert.assertThat(integerIterator.next(), Is.is(2));

        Assert.assertThat(integerIterator.hasNext(), Is.is(true));
        Assert.assertThat(integerIterator.next(), Is.is(1));

        Assert.assertThat(integerIterator.hasNext(), Is.is(true));
        Assert.assertThat(integerIterator.next(), Is.is(3));

        Assert.assertThat(integerIterator.hasNext(), Is.is(true));
        Assert.assertThat(integerIterator.next(), Is.is(4));

        Assert.assertThat(integerIterator.hasNext(), Is.is(false));

    }
}