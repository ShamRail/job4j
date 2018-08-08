package ru.job4j.lambda;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class FunctionsTest {

    @Test
    public void whenCheckLinearFucntion() {
        Functions functions = new Functions();
        List<Double> result = functions.linearFunction(1, 3);
        Assert.assertThat(result.get(0) + result.get(1) + result.get(2),
                Is.is(6.0));
    }

    @Test
    public void whenCheckSquareFunction() {
        Functions functions = new Functions();
        List<Double> result = functions.squareFunction(1, 3);
        Assert.assertThat(result.get(0) + result.get(1) + result.get(2),
                Is.is(14.0));
    }
}
