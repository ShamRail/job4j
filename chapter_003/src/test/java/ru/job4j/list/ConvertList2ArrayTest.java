package ru.job4j.list;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertList2ArrayTest {
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0 ,0}
        };
        assertThat(result, is(expect));
    }
    @Test
    public void when4ElelemtsThen4() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4), 2
        );
        int[][] expect = {
                {1, 2},
                {3, 4}
        };
        assertThat(result, is(expect));
    }
    @Test
    public void whenConvertListOfArraysToOneList() {
        ConvertList2Array convertlList = new ConvertList2Array();
        List<int[]> list = Arrays.asList(new int[]{1, 2}, new int[]{3, 4, 5, 6}, new int[]{7, 8, 9});
        Assert.assertThat(convertlList.convert(list), Is.is(Arrays.asList(1, 2, 3, 4 , 5 , 6, 7, 8, 9)));
    }
}