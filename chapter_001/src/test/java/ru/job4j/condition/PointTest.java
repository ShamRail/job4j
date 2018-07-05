package ru.job4j.condition;

import org.junit.Test;
import org.hamcrest.number.IsCloseTo;
import org.junit.Assert;

public class PointTest {
    @Test
    public void whenX1_1_X2_4_Y1_2_Y2_6Then() {
        Point point = new Point(1, 2);
        Point that = new Point(4, 6);
        double result = point.distanceTo(that);
        Assert.assertThat(result, IsCloseTo.closeTo(5, 0.1));
    }
}
