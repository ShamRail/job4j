package ru.job4j.tracker;

import java.util.Scanner;

public class ValidateInput implements Input {
    private Scanner scanner = new Scanner(System.in);
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = new ConsoleInput().ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Пожалуйста, выберите индекс внутри границ массива.");
            } catch (NumberFormatException nfe) {
                System.out.println("Пожалуйста, введите корректные данные.");
            }
        } while (invalid);
        return value;
    }

    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
