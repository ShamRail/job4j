package ru.job4j.tictactoe.logic;

/**
 * Описывает способ игрового хода.
 */
public interface Moveable {

    /**
     * Соверашает ход.
     * @return true ход выполнен, иначе false.
     */
    boolean move();

}
