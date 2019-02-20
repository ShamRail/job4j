package ru.job4j.tictactoe.io.console;

import ru.job4j.tictactoe.game.Board;
import ru.job4j.tictactoe.io.Output;

/**
 * Вывод в консоль.
 */
public class ConsolePrinter implements Output {

    /**
     * Игровая доска.
     */
    private Board board;

    public ConsolePrinter(Board board) {
        this.board = board;
    }

    /**
     * Вывод сообщения.
     * @param message сообщение.
     */
    @Override
    public void writeMessage(String message) {
        System.out.println(message);
    }

    /**
     * Печать доски.
     */
    @Override
    public void writeBoard() {
        int boardSize = board.getSize();
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                System.out.print(String.format("%s|", board.getCellState(row, column)));
            }
            System.out.println();
        }
    }

}
