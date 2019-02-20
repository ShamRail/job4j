package ru.job4j.tictactoe.io.console;

import ru.job4j.tictactoe.io.Input;
import java.util.Scanner;

/**
 * Ввод с консоли.
 */
public class ConsoleInput implements Input {

    /**
     * Способ ввода.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод запращивает у пользователя и данные и ожидает от него ответа.
     * @param question вопрос пользователю.
     * @return ответ пользователя.
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        return scanner.next();
    }

}
