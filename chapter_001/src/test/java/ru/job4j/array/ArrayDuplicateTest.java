package ru.job4j.array;

import org.junit.Assert;
import org.junit.Test;
import org.hamcrest.collection.IsArrayContainingInAnyOrder;


public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] input = {"Rail", "Rail", "Arina", "Arina", "Alina"};
        String[] expect = {"Rail", "Arina", "Alina"};
        String[] result = arrayDuplicate.remove(input);
        Assert.assertThat(
                result, IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(expect)
        );
    }
    @Test
    public void whenAllOneRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] input = {"One", "One", "One", "One", "One"};
        String[] expect = {"One"};
        String[] result = arrayDuplicate.remove(input);
        Assert.assertThat(
                result, IsArrayContainingInAnyOrder.arrayContainingInAnyOrder(expect)
        );
    }
}