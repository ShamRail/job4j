package ru.job4j.loop;

/**
 * Factorial
 * @version 1.0
 * @since 06.07.2018
 * @author Rail Shamsemukhametov
 * */

public class Factorial {

    /**
     * calc
     * Метод, считающий факториал числа
     * @param n - число
     * @return факториал числа
     * */

    public int calc(int n) {
        int result = 1;
        if (n != 0) {
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
        }
        return  result;
    }
}
