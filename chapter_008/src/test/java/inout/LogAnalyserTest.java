package inout;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class LogAnalyserTest {

    @Test
    public void simpleTest() throws IOException {
        LinkedList<String> lines = new LinkedList<>(Arrays.asList(
                "200 10:56:01",
                "500 10:57:01",
                "400 10:58:01",
                "200 10:59:01",
                "500 11:01:02",
                "200 11:02:02"));
        Path path = Files.createTempFile("in", ".tmp");
        Files.write(path, lines, StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        Path outPath = Files.createTempFile("out", ".tmp");
        LogAnalyser logAnalyser = new LogAnalyser();
        logAnalyser.availableTime(path, outPath);
        List<String> result = Files.readAllLines(outPath);
        Assert.assertThat(result.get(0), Is.is("10:57:01;10:59:01"));
        Assert.assertThat(result.get(1), Is.is("11:01:02;11:02:02"));
    }

}