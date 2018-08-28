package list;

import org.junit.Before;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {
    SimpleLinkedList<Integer> nums = new SimpleLinkedList<>();

    @Before
    public void setUp() {
        nums.add(1);
        nums.add(2);
        nums.add(null);
        nums.add(4);
        nums.add(5);
    }

    @Test
    public void whenIndexIsInBoundOfListThemReturnElemsAtIndex() {
        assertThat(nums.get(0), is(5));
        assertThat(nums.get(1), is(4));
        assertThat(nums.get(2), is((Integer) null));
        assertThat(nums.get(3), is(2));
        assertThat(nums.get(4), is(1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenIndexIsOutOfBoundsMustBeException() {
        nums.get(5);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenListModifiedAfterCreatingIteratorMustBeException() {
        Iterator<Integer> integerIterator = nums.iterator();
        nums.add(7);
        integerIterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorWentOutListAndIvokeNextMustBeException() {
        Iterator<Integer> integerIterator = nums.iterator();
        for (int i = 0; i < 5; i++) {
            integerIterator.next();
        }
        integerIterator.next();
    }

    @Test
    public void whenTestHasNextAndNextMethods() {
        Iterator<Integer> integerIterator = nums.iterator();
        assertThat(integerIterator.hasNext(), is(true));
        assertThat(integerIterator.next(), is(5));
        assertThat(integerIterator.hasNext(), is(true));
        assertThat(integerIterator.next(), is(4));
        assertThat(integerIterator.hasNext(), is(true));
        assertThat(integerIterator.next(), is((Integer) null));
        assertThat(integerIterator.hasNext(), is(true));
        assertThat(integerIterator.next(), is(2));
        assertThat(integerIterator.hasNext(), is(true));
        assertThat(integerIterator.next(), is(1));
        assertThat(integerIterator.hasNext(), is(false));






    }
}