package filefinder.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Лог в файл.
 */
public class FileLogger implements LogWriter<String, List<File>> {

    /**
     * Записывает абсолютные имена файлов в лог.
     * Если файл лог существовал, то он перезаписывается.
     * @param destination назначение записи.
     * @param source источник записи.
     */
    @Override
    public void write(String destination, List<File> source) throws IOException {
        try (OutputStreamWriter out = new FileWriter(destination)) {
            source.forEach((file) -> {
                try {
                    out.write(file.getAbsolutePath());
                    out.write(System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
