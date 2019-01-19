package inout;

import java.io.File;
import java.util.*;

public class FileFinder {

    public List<File> getFiles(String parent, List<String> requiredExtensions) {
        List<File> result = new LinkedList<>();
        File currentLevel = new File(parent);
        LinkedList<File> queue = new LinkedList<>(Arrays.asList(Objects.requireNonNull(currentLevel.listFiles())));
        while (!queue.isEmpty()) {
            currentLevel = queue.pollFirst();
            if (currentLevel != null && currentLevel.isFile()) {
                String fileName = currentLevel.getName();
                String extension = fileName.substring(fileName.lastIndexOf('.'));
                if (requiredExtensions.contains(extension)) {
                    result.add(currentLevel);
                }
            }
            if (currentLevel != null && currentLevel.isDirectory()) {
                Arrays.stream(Objects.requireNonNull(currentLevel.listFiles())).forEach(queue::addLast);
            }
        }
        return result;
    }

}
