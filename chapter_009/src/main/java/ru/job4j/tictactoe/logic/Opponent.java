package ru.job4j.tictactoe.logic;

/**
 * Описывает, что игрок значет о стороне противники.
 */
public interface Opponent {

    /**
     * Возвращает сторону противника.
     * @return сторона противника.
     */
    String getOpponentSide();

}
