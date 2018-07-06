package ru.job4j.loop;

import org.junit.Test;
import org.junit.Assert;
import org.hamcrest.core.Is;

public class PaintTest {
    @Test
    public void whenHeightThreeThen() {
        Paint paint = new Paint();
        String result = paint.pyramid(3);
        final String line = System.getProperty("line.separator");
        String expected = String.format("  ^  \r\n ^^^ \r\n^^^^^\r\n", line, line, line);
        Assert.assertThat(result, Is.is(expected));
    }
}
