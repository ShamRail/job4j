package ru.job4j.algo;

import java.util.*;

public class Algorithm {

    /**
     * Задача решается методом динамическоого программирования.
     * Для отслеживания предыдущего решения используем table.
     * Это таблица отражает измение между суммами к общей сумме. Т.е.
     * A - B = d
     * A + B = sum
     * Наша задача найти максимальное A = d + B при d = 0
     * Причем на каждом шаге мы смотрим как нам выгоднее поступить
     * 1. Оставить вес как прежде
     * 2. Добавить вес влево
     * 3. Добавить вес вправо
     * @param weights
     * @return
     */

    public int getMaxWeight(int[] weights) {
        HashMap<Integer, Integer> table = new HashMap<>();
        table.put(0, 0);
        for (int weight : weights) {
            HashMap<Integer, Integer> copyTable = new HashMap<>(table);
            for (Map.Entry<Integer, Integer> set : table.entrySet()) {

                int key = set.getKey();
                int totalWeight = set.getValue();
                int sum = totalWeight + weight;

                int left = Math.abs(key - weight);
                int right = Math.abs(key + weight);
                int leftValue = copyTable.get(left) == null ? 0 : copyTable.get(left);
                int rightValue = copyTable.get(right) == null ? 0 : copyTable.get(right);

                copyTable.put(left, Math.max(leftValue, sum));
                copyTable.put(right, Math.max(rightValue, sum));
            }
            table = copyTable;
        }
        return (table.get(0) / 2) * 2;
    }

}
