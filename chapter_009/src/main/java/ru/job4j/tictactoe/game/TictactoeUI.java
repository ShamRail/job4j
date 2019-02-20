package ru.job4j.tictactoe.game;

import ru.job4j.tictactoe.io.Input;
import ru.job4j.tictactoe.io.Output;
import ru.job4j.tictactoe.logic.Player;

/**
 * Точка запуска приложения.
 */

public class TictactoeUI {

    /**
     * Метод, запускающий игру.
     * Клиент должен знать.
     * @param board где, играет.
     * @param input куда вводит данные.
     * @param output откуда их получает.
     * @param player с кем играет.
     */
    public void startGame(Board board, Input input, Output output, Player player) {
        new Tictactoe(board, input, output, player).begin();
    }

}
