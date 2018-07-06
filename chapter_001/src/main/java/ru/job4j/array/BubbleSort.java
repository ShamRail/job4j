package ru.job4j.array;

/**
 * BubbleSort.
 * @version 1.0.
 * @since 06.07.2018.
 * @author Rail Shamsemukhametov.
 * */

public class BubbleSort {

    /**
     * sort
     * Сортировка массива пузырьком по возрастанию
     * @param array - исходный массив
     * @return отсортированный массив
     * */

    public int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}
