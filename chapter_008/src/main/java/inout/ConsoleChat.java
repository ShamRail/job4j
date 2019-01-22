package inout;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class ConsoleChat {

    public ConsoleChat() {
        preparePhraseFile();
    }

    private void preparePhraseFile() {
        String[] phrases = {
                "What can I do for you?",
                "Keep in touch.",
                "Good job!",
                "It is a good idea.",
                "It doesn't matter.",
                "Be careful.",
                "Where have you been?",
                "What's your nickname?",
                "My friends call me Console Bot.",
                "Nice to see you.",
                "I think so.",
                "That's the whole point.",
                "It's going to be all right.",
                "Let happen whatever would happen.",
                "You're cool!"
        };
        try (Writer writer = new FileWriter("phrases.txt")) {
            for (String phrase : phrases) {
                writer.write(phrase);
                writer.write(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        File file = new File("phrases.txt");
        List<String> phrases = new ArrayList<>(Files.readAllLines(file.toPath()));
        Scanner scanner = new Scanner(System.in);

        String ask;
        String botAnswer;
        boolean toAsk;
        boolean stopFlag = false;

        try (Writer writer = new FileWriter("phraseLogs.txt")) {
            do {
                System.out.print("i: ");
                ask = scanner.nextLine();
                writer.write(String.format("i: %s%s", ask, System.lineSeparator()));
                if ("stop".equals(ask) || "finish".equals(ask)) {
                    stopFlag = true;
                }
                if ("continue".equals(ask)) {
                    stopFlag = false;
                }
                toAsk = !stopFlag;
                if (toAsk) {
                    botAnswer = phrases.get((int) (Math.random() * 15));
                    System.out.print("bot: ");
                    System.out.println(botAnswer);
                    writer.write(String.format("bot: %s%s", botAnswer, System.lineSeparator()));
                }
            } while (!"finish".equals(ask));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new ConsoleChat().start();
    }
}
