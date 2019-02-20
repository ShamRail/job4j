package ru.job4j.tictactoe.io;

/**
 * Описывает способ вывода.
 */
public interface Output {

    /**
     * Вывод сообщения.
     * @param message сообщение.
     */
    void writeMessage(String message);

    /**
     * Вывод доски.
     */
    void writeBoard();

}
