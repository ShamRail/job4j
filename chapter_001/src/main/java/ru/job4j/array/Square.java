package ru.job4j.array;

/**
 * Square.
 * @version 1.0.
 * @since 06.07.2018.
 * @author Rail Shamsemukhametov.
 * */

public class Square {

    /**
     * calculate.
     * Метод , заполняющий элементы массива квадратами от 1 до bound.
     * @param bound - конец диапозона
     * @return заполненный массив
     * */

    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 1; i < bound + 1; i++) {
            rst[i - 1] = i * i;
        }
        return rst;
    }
}
