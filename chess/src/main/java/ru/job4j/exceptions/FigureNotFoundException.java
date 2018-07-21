package ru.job4j.exceptions;

/**
 * FigureNotFoundException.
 * Класс исключение.
 * Вызывается, если фигура не найдена.
 * @version 1.0
 * @since 20.07.2018.
 * @author Rail Shamsemukhametov.
 * */
public class FigureNotFoundException extends Exception {
    private String message;
    public FigureNotFoundException(String msg) {
        this.message = msg;
    }
    public String getMessage() {
        return this.message;
    }
}
