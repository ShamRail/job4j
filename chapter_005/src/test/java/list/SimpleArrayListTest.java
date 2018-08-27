package list;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SimpleArrayListTest {
    SimpleArrayList<Integer> numbers = new SimpleArrayList<>();

    @Before
    public void setup() {
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
    }

    @Test
    public void whenTestAddAndGetMethods() {
        Assert.assertThat(numbers.get(0), Is.is(5));
        Assert.assertThat(numbers.get(1), Is.is(4));
        Assert.assertThat(numbers.get(2), Is.is(3));
        Assert.assertThat(numbers.get(3), Is.is(2));
        Assert.assertThat(numbers.get(4), Is.is(1));
    }

    @Test
    public void whenDeleteFirstElement() {
        Assert.assertThat(numbers.delete(), Is.is(5));
        Assert.assertThat(numbers.get(0), Is.is(4));
        Assert.assertThat(numbers.get(1), Is.is(3));
        Assert.assertThat(numbers.get(2), Is.is(2));
        Assert.assertThat(numbers.get(3), Is.is(1));
    }

    @Test
    public void whenDeleteOneElementFromListThatContainsOneElement() {
        numbers = new SimpleArrayList<>();
        numbers.add(1);
        Assert.assertThat(numbers.delete(), Is.is(1));
        Assert.assertThat(numbers.size(), Is.is(0));
    }

    @Test
    public void whenDeleteOneElementFromListThatContainsTwoElements() {
        numbers = new SimpleArrayList<>();
        numbers.add(1);
        numbers.add(2);
        Assert.assertThat(numbers.delete(), Is.is(2));
        Assert.assertThat(numbers.get(0), Is.is(1));
    }

    @Test
    public void whenDeleteOneLastElementFromListThatContainsOneElement() {
        numbers = new SimpleArrayList<>();
        numbers.add(1);
        Assert.assertThat(numbers.deleteLast(), Is.is(1));
        Assert.assertThat(numbers.size(), Is.is(0));
    }

    @Test
    public void whenDeleteOneLastElementFromListThatContainsTwoElements() {
        numbers = new SimpleArrayList<>();
        numbers.add(1);
        numbers.add(2);
        Assert.assertThat(numbers.deleteLast(), Is.is(1));
        Assert.assertThat(numbers.get(0), Is.is(2));
    }

    @Test
    public void whenDeleteLastElemet() {
        Assert.assertThat(numbers.deleteLast(), Is.is(1));
        Assert.assertThat(numbers.get(0), Is.is(5));
        Assert.assertThat(numbers.get(1), Is.is(4));
        Assert.assertThat(numbers.get(2), Is.is(3));
        Assert.assertThat(numbers.get(3), Is.is(2));
    }

    @Test
    public void whenListIsEmptyDeleteMethodsMustReturnNull() {
        numbers = new SimpleArrayList<>();
        Assert.assertThat(numbers.delete(), Is.is((Integer) null));
        Assert.assertThat(numbers.deleteLast(), Is.is((Integer) null));
    }
}