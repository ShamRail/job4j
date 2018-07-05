package ru.job4j.converter;

import  org.junit.Test;
import  org.hamcrest.core.Is;
import  org.junit.Assert;


public class ConverterTest {
    @Test
    public void when60RubleToDollarThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(60);
        Assert.assertThat(result, Is.is(1));
    }

    @Test
    public void when70RubleToEuroThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(70);
        Assert.assertThat(result, Is.is(1));
    }
    @Test
    public void when1DollarToRuble() {
        Converter converter = new Converter();
        int result = converter.dollarToRuble(1);
        Assert.assertThat(result, Is.is(60));
    }

    @Test
    public void when1EuroToRuble() {
        Converter converter = new Converter();
        int result = converter.euroToRuble(1);
        Assert.assertThat(result, Is.is(70));
    }
}
