package ru.job4j.additionalquestions;

import org.junit.Test;
import org.hamcrest.core.Is;
import org.junit.Assert;

public class SegmentsTest {

    @Test
    public void whenA1B3C2D4() {
        Segments segments = new Segments(1, 3, 2, 4);
        Assert.assertThat(
                segments.isSegmentsCrossing(), Is.is(true)
        );
    }

    @Test
    public void whenA2B4C1D3() {
        Segments segments = new Segments(2, 4, 1, 3);
        Assert.assertThat(
                segments.isSegmentsCrossing(), Is.is(true)
        );
    }

    @Test
    public void whenA1B4C2D3() {
        Segments segments = new Segments(1, 4, 2, 3);
        Assert.assertThat(
                segments.isSegmentsCrossing(), Is.is(true)
        );
    }

    @Test
    public void whenA2B3C1D4() {
        Segments segments = new Segments(2, 3, 1, 4);
        Assert.assertThat(
                segments.isSegmentsCrossing(), Is.is(true)
        );
    }

    @Test
    public void whenA1B2C2D4() {
        Segments segments = new Segments(1, 2, 2, 4);
        Assert.assertThat(
                segments.isSegmentsCrossing(), Is.is(true)
        );
    }

    @Test
    public void whenA2B3C1D2() {
        Segments segments = new Segments(2, 3, 1, 2);
        Assert.assertThat(
                segments.isSegmentsCrossing(), Is.is(true)
        );
    }

    @Test
    public void whenA1B2C1D2() {
        Segments segments = new Segments(1, 2, 1, 2);
        Assert.assertThat(
                segments.isSegmentsCrossing(), Is.is(true)
        );
    }

    @Test
    public void whenNoCrossingA1B2C3D4() {
        Segments segments = new Segments(1, 2, 3, 4);
        Assert.assertThat(
                segments.isSegmentsCrossing(), Is.is(false)
        );
    }

    @Test
    public void whenNoCrossingA3B4C1D4() {
        Segments segments = new Segments(3, 4, 1, 2);
        Assert.assertThat(
                segments.isSegmentsCrossing(), Is.is(false)
        );
    }


}
