package ru.job4j.exceptions;

/**
 * OccupiedWayException.
 * Класс исключение.
 * Вызывается, если на месте перемещения уже стоит фигура.
 *  @version 1.0
 *  @since 20.07.2018.
 *  @author Rail Shamsemukhametov.
 * */

public class OccupiedWayException extends Exception {
    private String message;
    public OccupiedWayException(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }
}
