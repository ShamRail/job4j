package ru.job4j.max;

/**
 * Max
 * Находит максимум среди чисел
 * @version 1.0
 * @since 05.07.2018
 * @author Rail Shamsemukhametov
 * */

public class Max {
    /**
     * max.
     * Находит максимум среди 2 - х чисел.
     * @param first - первое число.
     * @param second - второе число.
     * @return максимум среди чисел.
     */

    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * max.
     * Находит максимум среди 3 - х чисел.
     * @param first - первое число.
     * @param second - второе число.
     * @param third - третье число.
     * @return максимум среди чисел.
     */

    public int max(int first, int second, int third) {
        return this.max(this.max(first, second), third);
    }
}
