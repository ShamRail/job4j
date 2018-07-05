package ru.job4j.calculate;

import org.hamcrest.number.IsCloseTo;
import  org.junit.Test;
import  org.hamcrest.core.Is;
import  org.junit.Assert;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class FitTest {

    @Test
    public void manWeight() {
        Fit fit = new Fit();
        double weight = fit.manWeight(180);
        Assert.assertThat(weight, IsCloseTo.closeTo(92.0, 0.1));
    }

    @Test
    public void womanWeight() {
        Fit fit = new Fit();
        double weight = fit.womanWeight(170);
        Assert.assertThat(weight, IsCloseTo.closeTo(69.0, 0.1));
    }
}
