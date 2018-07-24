package ru.job4j.coffeemachine;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class CoffeeMachineTest {
    @Test
    public void whenValue50Price35() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.changes(50, 35);
        int[] expect = {10, 5};
        Assert.assertThat(result, Is.is(expect));
    }
    @Test
    public void whenValue100Price55() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.changes(100, 55);
        int[] expect = {10, 10, 10, 10, 5};
        Assert.assertThat(result, Is.is(expect));
    }
    @Test
    public void whenSurrender0() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] result = coffeeMachine.changes(55, 55);
        int[] expect = new int[0];
        Assert.assertThat(result, Is.is(expect));
    }
}
