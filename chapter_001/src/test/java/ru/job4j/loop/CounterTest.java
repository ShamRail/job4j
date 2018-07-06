package ru.job4j.loop;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;


public class CounterTest {
    @Test
    public void whenSumEvenNumbersFromOneToTenThenThirty() {
        Counter counter = new Counter();
        Assert.assertThat(
                counter.add(1, 10), Is.is(30)
        );
    }
}