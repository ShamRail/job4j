package inout;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LogAnalyser {

    public void availableTime(Path in, Path out) {
        try (BufferedReader reader = new BufferedReader(new FileReader(in.toFile()))) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            List<String> cash = new LinkedList<>();
            String time = null;
            for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
                String[] pair = lines.get(lineIndex).split("\\s+");
                int type = Integer.parseInt(pair[0]);
                if (time == null && (type == 400 || type == 500)) {
                    time = pair[1];
                }
                if (time != null && type < 400 || endOfLines(lineIndex, lines)) {
                    cash.add(String.format("%s;%s", time, pair[1]));
                    time = null;
                }
            }
            Files.write(out, cash, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean endOfLines(int index, List<String> lines) {
        return index == lines.size() - 1;
    }
}
