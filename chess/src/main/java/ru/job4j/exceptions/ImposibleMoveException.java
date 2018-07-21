package ru.job4j.exceptions;

/**
 * ImposibleMoveException.
 * Класс исключение.
 * Вызывается, если фигуру невозможно переместить.
 *  @version 1.0
 *  @since 20.07.2018.
 *  @author Rail Shamsemukhametov.
 * */

public class ImposibleMoveException extends Exception {
    private String message;
    public ImposibleMoveException(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }
}