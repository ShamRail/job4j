package ru.job4j.additionalquestions;

import org.junit.Test;
import org.junit.Assert;
import org.hamcrest.core.Is;


public class AddArraysTest {
    @Test
    public void whenBothWithOneLenght() {
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        int[] expect = {1, 2, 3, 4, 5, 6};
        AddArrays addArrays = new AddArrays(arr1, arr2);
        Assert.assertThat(
                addArrays.addArrays(), Is.is(expect)
        );
    }

    @Test
    public void whenBothWithDiffrentLenght() {
        int[] arr1 = {4, 8, 12, 14, 23, 85};
        int[] arr2 = {2, 4, 8, 9, 12, 16};
        int[] expect = {2, 4, 4, 8, 8, 9, 12, 12, 14, 16, 23, 85};
        AddArrays addArrays = new AddArrays(arr1, arr2);
        Assert.assertThat(
                addArrays.addArrays(), Is.is(expect)
        );
    }
}
