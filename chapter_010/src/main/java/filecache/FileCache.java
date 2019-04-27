package filecache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileCache {

    /**
     * Way to directory.
     * */
    private final Path path;

    /**
     * Cache represented by file name and it's content as list of strings.
     * */
    private Map<String, List<String>> cache;

    /**
     * Soft reference to cache.
     * */
    private SoftReference<Map<String, List<String>>> softReference;

    public FileCache(Path path) {
        this.path = path;
        this.cache = new HashMap<>();
        this.softReference = new SoftReference<>(cache);
    }

    /**
     * Retrieve file contents if cache contains file.
     * Otherwise load file to cache and retrieve data.
     * */
    public List<String> getFile(String fileName) throws IOException {
        List<String> result;
        if (cache.containsKey(fileName)) {
            result = softReference.get().get(fileName);
        } else {
            List<String> fileContent = Files.readAllLines(Paths.get(path.toString(), fileName));
            softReference.get().put(fileName, fileContent);
            result = fileContent;
        }
        return result;
    }

}
