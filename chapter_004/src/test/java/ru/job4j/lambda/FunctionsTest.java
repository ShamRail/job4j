package ru.job4j.lambda;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

public class FunctionsTest {

    @Test
    public void whenCheckLinearFucntion() {
        Functions functions = new Functions();
        Assert.assertThat(functions.linearFunction(1, 3),
                Is.is(Arrays.asList(1.0, 2.0, 3.0)));
    }

    @Test
    public void whenCheckSquareFunction() {
        Functions functions = new Functions();
        Assert.assertThat(functions.squareFunction(1, 3),
                Is.is(Arrays.asList(1.0, 4.0, 9.0)));
    }

    @Test
    public void whenCheckLogFunction() {
        Functions functions = new Functions();
        Assert.assertThat(functions.logarithmFunction(1, 3),
                Is.is(Arrays.asList(0.0, 0.6931471805599453, 1.0986122886681098)));
    }
}
