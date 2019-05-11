package olymp;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Task8Test {

    @Test
    public void test1() {
        int[] data = {40, 80, 50, 60};
        TreeSet<Integer> exPoints = new TreeSet<>();
        int expect = 20;
        TreeSet<Integer> expectExPoint = new TreeSet<>(Arrays.asList(3));
        Task8 task8 = new Task8();
        int result = task8.calculate(data, 1, exPoints);
        Assert.assertThat(result, Is.is(expect));
        Assert.assertThat(exPoints, Is.is(expectExPoint));
    }

    @Test
    public void test2() {
        int[] data = {12, 96, 6, 34, 73};
        TreeSet<Integer> exPoints = new TreeSet<>();
        int expect = 90;
        TreeSet<Integer> expectExPoint = new TreeSet<>(Arrays.asList(1, 4, 5));
        Task8 task8 = new Task8();
        int result = task8.calculate(data, 3, exPoints);
        Assert.assertThat(result, Is.is(expect));
        Assert.assertThat(exPoints, Is.is(expectExPoint));
    }

    @Test
    public void test3() {
        int[] data = {2, 2, 2};
        TreeSet<Integer> exPoints = new TreeSet<>();
        int expect = 0;
        TreeSet<Integer> expectExPoint = new TreeSet<>(Arrays.asList(1));
        Task8 task8 = new Task8();
        int result = task8.calculate(data, 1, exPoints);
        Assert.assertThat(result, Is.is(expect));
        Assert.assertThat(exPoints, Is.is(expectExPoint));
    }

}