package inout;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FileFinderTest {

    @Ignore
    @Test
    public void test() throws Exception {
        String initDir = System.getProperty("java.io.tmpdir");

        File rootDir = new File(String.format("%s\\test dir", initDir));
        File firstDirectory = new File(String.format("%s\\firstDir", rootDir.getPath()));
        File secondDirectory = new File(String.format("%s\\secondDir", rootDir.getPath()));
        File dirInFirstDirectory = new File(String.format("%s\\new dir", firstDirectory.getPath()));

        File rootFile = new File(String.format("%s\\rootFile.xml", rootDir.getPath()));
        File firstFile = new File(String.format("%s\\firstFile.txt", dirInFirstDirectory.getPath()));
        File secondFile = new File(String.format("%s\\secondFile.doc", secondDirectory.getPath()));

        rootDir.mkdir();
        firstDirectory.mkdir();
        secondDirectory.mkdir();
        dirInFirstDirectory.mkdir();

        rootFile.createNewFile();
        firstFile.createNewFile();
        secondFile.createNewFile();

        List<String> extensions = new LinkedList<>(Arrays.asList(".txt", ".doc", ".xml"));
        List<File> expected = new LinkedList<>(Arrays.asList(rootFile, secondFile, firstFile));
        List<File> result = new FileFinder().getFiles(rootDir.getPath(), extensions);
        Assert.assertThat(result, Is.is(expected));
    }

}