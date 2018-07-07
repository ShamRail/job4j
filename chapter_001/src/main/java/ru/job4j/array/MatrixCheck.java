package ru.job4j.array;

/**
 * Matrix.
 * @version 1.0.
 * @since 06.07.2018.
 * @author Rail Shamsemukhametov.
 * */

public class MatrixCheck {

    /**
     * mono.
     * Метод, проверящий одинаковые ли элементы по диагоналям.
     * fistfromLeft - верхний элемент с левого угла.
     * fistfromRight - верхний элемент с правого угла.
     * @param data - исходная матрица.
     * @return результат
     * */

    public boolean mono(boolean[][] data) {

        boolean result = true;
        boolean fistfromLeft = data[0][0];
        boolean fistfromRight = data[0][data.length - 1];

        for (int i = 1; i < data.length; i++) {
            if (data[i][i] != fistfromLeft
                    ||  data[data.length - 1 - i][i] != fistfromRight) {
                result = false;
                break;
            }
        }
        return result;
    }

}