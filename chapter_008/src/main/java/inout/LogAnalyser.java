package inout;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class LogAnalyser {

    public void availableTime(Path in, Path out) {
        try (BufferedReader reader = new BufferedReader(new FileReader(in.toFile()));
             BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()))) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            String time = null;
            for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
                String[] pair = lines.get(lineIndex).split("\\s+");
                int type = Integer.parseInt(pair[0]);
                if (time == null && (type == 400 || type == 500)) {
                    time = pair[1];
                    writer.write(time);
                    writer.write(';');
                }
                if (time != null && type < 400 || lineIndex == lines.size() - 1) {
                    writer.write(pair[1]);
                    writer.write(System.lineSeparator());
                    time = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
