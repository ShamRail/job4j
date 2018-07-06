package ru.job4j.array;

/**
 * Matrix.
 * @version 1.0.
 * @since 06.07.2018.
 * @author Rail Shamsemukhametov.
 * */

public class Matrix {

    /**
     * Multiple
     * Метод , строящий таблицу умножения размерностью size
     * @param size - размерность таблицы
     * @return таблица умножения в матричном виде
     * */

    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}