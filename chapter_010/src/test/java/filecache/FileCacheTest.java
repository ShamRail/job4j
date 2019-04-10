package filecache;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FileCacheTest {

    @Test
    public void whenCacheEmptyThenLoad() throws IOException {
        Path path = Files.createTempFile("test", ".tmp");
        Files.write(path, Collections.singletonList("one"), StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        FileCache fileCache = new FileCache(path.getParent());
        List<String> result = new LinkedList<>(Collections.singletonList("one"));
        Assert.assertThat(fileCache.getFile(path.getFileName().toString()), Is.is(result));
    }

    @Test
    public void whenCacheContainsThenLoad() throws IOException {
        Path path = Files.createTempFile("test", ".tmp");
        Files.write(path, Collections.singletonList("one"), StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        FileCache fileCache = new FileCache(path.getParent());
        fileCache.getFile(path.getFileName().toString());
        List<String> result = new LinkedList<>(Collections.singletonList("one"));
        Assert.assertThat(fileCache.getFile(path.getFileName().toString()), Is.is(result));
    }

}