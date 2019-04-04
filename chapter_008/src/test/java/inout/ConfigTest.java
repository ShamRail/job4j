package inout;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.*;
import java.util.Collections;

public class ConfigTest {

    @Test
    public void whenLoadAndGet() throws IOException {
        Path path = Files.createTempFile("test", ".tmp");
        Files.write(path, Collections.singletonList("name=rail"), StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        Config config = new Config(path.toString());
        Assert.assertThat(config.value("name"), Is.is("rail"));
    }

    @Test
    public void whenPutAndGet() throws IOException {
        Path path = Files.createTempFile("test", ".tmp");
        Files.write(path, Collections.singletonList("name=rail"), StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        Config config = new Config(path.toString());
        config.put("university", "ugatu");
        Assert.assertThat(config.value("university"), Is.is("ugatu"));
    }

    @Test
    public void whenPutExistingThenReplaceOld() throws IOException {
        Path path = Files.createTempFile("test", ".tmp");
        Files.write(path, Collections.singletonList("name=rail"), StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        Config config = new Config(path.toString());
        config.put("name", "petr");
        Assert.assertThat(config.value("name"), Is.is("petr"));
    }

}