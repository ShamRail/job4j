package ru.job4j.algo;

import org.junit.Assert;
import org.junit.Test;

public class AlgorithmTest {

    @Test
    public void whenWeight1and2ThenReturn0() {
        Assert.assertEquals(0, new Algorithm().getMaxWeight(new int[] {1, 2}));
    }

    @Test
    public void whenWeight1and1ThenReturn2() {
        Assert.assertEquals(2, new Algorithm().getMaxWeight(new int[] {1, 1}));
    }

    @Test
    public void whenWeight1and2and3and6thenReturn12() {
        Assert.assertEquals(12, new Algorithm().getMaxWeight(new int[] {1, 2, 3, 6}));
    }

    @Test
    public void whenWeight3and4and3and3and2thenReturn12() {
        Assert.assertEquals(12, new Algorithm().getMaxWeight(new int[] {3, 4, 3, 3, 2}));
    }

    @Test
    public void test() {
        Assert.assertEquals(20, new Algorithm().getMaxWeight(new int[] {1, 2, 3, 4, 5, 6}));
    }

}