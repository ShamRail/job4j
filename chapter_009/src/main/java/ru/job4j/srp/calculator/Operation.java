package ru.job4j.srp.calculator;

/**
 * Описывает функцию, которая может зависеть от многих переменных.
 * @param <T>
 */

public interface Operation<T> {
    /**
     * Применяет дейсвие для заданных аргументов.
     * @param args аргументы.
     * @return результат работы функции.
     */
    T apply(T... args);
}
