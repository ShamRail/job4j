package list;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleDynamicArrayListTest {
    private SimpleDynamicArrayList<Integer> numbers = new SimpleDynamicArrayList<>();

    @Before
    public void setUp() {
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add((Integer) null);
    }

    @Test
    public void whenParamIndexOfGetMethotIsInBoundsThenReturnValuesAtThatIndex() {
        Assert.assertThat(numbers.get(0), Is.is(1));
        Assert.assertThat(numbers.get(1), Is.is(2));
        Assert.assertThat(numbers.get(2), Is.is(3));
        Assert.assertThat(numbers.get(3), Is.is(4));
        Assert.assertThat(numbers.get(4), Is.is(5));
        Assert.assertThat(numbers.get(5), Is.is((Integer) null));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenIndexIsOutOfBoundsThenMustBeException() {
        numbers.get(-1);
    }

    @Test
    public void whenListFullToFillThenMustReSize() {
        numbers.add(7);
        numbers.add(8);
        numbers.add(9);
        numbers.add(10);
        numbers.add(11);
        Assert.assertThat(numbers.get(10), Is.is(11));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenChangeListAfterCreatingIteratorThenMustException() {
        Iterator<Integer> integerIterator = numbers.iterator();
        numbers.add(7);
        integerIterator.next();
    }
}