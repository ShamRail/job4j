package inout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipArchiver {

    private List<String> requiredExtension;

    public ZipArchiver(String[] extensions) {
        requiredExtension = convertToList(extensions);
    }

    public List<String> convertToList(String[] args) {
        return new LinkedList<>(Arrays.asList(args));
    }

    private List<File> getSavingFiles(String filePath) {
        File rootFile = new File(filePath);
        List<File> result = new LinkedList<>();
        LinkedList<File> files = new LinkedList<>(Arrays.asList(Objects.requireNonNull(rootFile.listFiles())));
        while (!files.isEmpty()) {
            File file = files.pollFirst();
            if (file != null && file.isFile()) {
                String fileName = file.getName();
                int spliterIndex = fileName.lastIndexOf('.');
                if (spliterIndex != -1 && requiredExtension.contains(fileName.substring(spliterIndex))) {
                    result.add(file);
                }
            }
            if (file != null && file.isDirectory()) {
                result.add(file);
                Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(files::addLast);
            }
        }
        return result;
    }

    public boolean zipDirectory(String dirPath, String outLocation) {
        boolean result = true;
        List<File> filesToZip = getSavingFiles(dirPath);
        if (!filesToZip.isEmpty()) {
            try (OutputStream fileStream = new FileOutputStream(outLocation);
                 ZipOutputStream zipStream = new ZipOutputStream(fileStream)) {
                 for (File file : filesToZip) {
                     ZipEntry zipEntry = getEntry(file, dirPath);
                     zipStream.putNextEntry(zipEntry);
                     writeFileIfFile(file, zipStream);
                     zipStream.closeEntry();
                 }
            } catch (Exception ex) {
                result = false;
                ex.printStackTrace();
            }
        }
        return result;
    }

    private ZipEntry getEntry(File file, String dirPath) {
        String fileAbsolutePath = file.getAbsolutePath();
        String fileRelativePath =  fileAbsolutePath.substring(dirPath.length() + 1);
        return (file.isFile()) ? new ZipEntry(fileRelativePath) : new ZipEntry(String.format("%s/", fileRelativePath));
    }

    private void writeFileIfFile(File file, ZipOutputStream zipStream) {
        if (file.isFile()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zipStream.write(buffer, 0, len);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String dirPath = args[0];
        String outPath = args[1];
        String[] ext = Arrays.copyOfRange(args, 2, args.length);
        ZipArchiver zipArchiver = new ZipArchiver(ext);
        zipArchiver.zipDirectory(dirPath, outPath);

    }
}