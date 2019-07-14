package ru.job4j.servlets;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;

import static org.junit.Assert.*;

public class StringConverterTest {

    @Test
    public void whenGetReaderThenReturnString() throws IOException {
        Reader input = new CharArrayReader("123".toCharArray());
        String out = StringConverter.convert(input);
        String expect = "123";
        Assert.assertThat(out, Is.is(expect));
    }

}