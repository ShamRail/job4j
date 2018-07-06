package ru.job4j.array;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class BubbleSortTest {
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {

        BubbleSort bubbleSort = new BubbleSort();
        int[] input = new int[] {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        int[] expect = new int[] {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        int[] result = bubbleSort.sort(input);
        Assert.assertThat(expect, Is.is(input));
    }
}