package ru.job4j.strategy;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {

    @Test
    public void whenDrawTriangle() {
        Triangle triangle = new Triangle(4);
        Assert.assertThat(
                triangle.draw(), Is.is(
                        new StringBuilder()
                        .append("*").append(System.lineSeparator())
                        .append("**").append(System.lineSeparator())
                        .append("***").append(System.lineSeparator())
                        .append("****").append(System.lineSeparator())
                        .toString())
        );
    }
}
