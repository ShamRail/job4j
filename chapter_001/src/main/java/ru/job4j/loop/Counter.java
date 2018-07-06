package ru.job4j.loop;

/**
 * Counter
 * @version 1.0
 * @since 06.07.2018
 * @author Rail Shamsemukhametov
 * */

public class Counter {

    /**
     * add
     * Метод , ищущий сумму четных чисел в диапозоне
     * @param start - начало диапозона
     * @param finish - конец диапозона , включая
     * @return сумму чисел
     * */

    public int add(int start, int finish) {
        int count = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                count += i;
            }
        }
        return  count;
    }
}
