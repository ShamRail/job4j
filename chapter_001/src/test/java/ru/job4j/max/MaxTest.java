package ru.job4j.max;

import org.junit.Test;
import org.junit.Assert;
import org.hamcrest.core.Is;

public class MaxTest {
    @Test
    public void whenCompare1And2ThenMax2() {
        Max max = new Max();
        Assert.assertThat(
                max.max(1, 2), Is.is(2)
        );
    }
    @Test
    public void  whenCompare2and1ThenMax2() {
        Max max = new Max();
        Assert.assertThat(
                max.max(2, 1), Is.is(2)
        );
    }
    @Test
    public void whenCompare2and2ThenMax2() {
        Max max = new Max();
        Assert.assertThat(
                max.max(2, 2), Is.is(2)
        );
    }
}
