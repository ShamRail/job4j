package ru.job4j.coffeemachine;

import java.util.Arrays;

public class CoffeeMachine {

    /**
     * changes.
     * Выдает сдачи наибольшем номиналом.
     * @param value вносимые деньги.
     * @param price цена кофе.
     * */

    public int[] changes(int value, int price) {
        int diffence = value - price;
        int count = 0;
        int[] result = new int[value - price];
        for (Nominal nominal : Nominal.values()) {
            int nom = nominal.getNominal();
            while (diffence / nom != 0) {
                diffence -= nom;
                result[count++] = nom;
            }
        }
        return Arrays.copyOf(result, count);
    }
}
