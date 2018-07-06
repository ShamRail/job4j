package ru.job4j.array;

import java.util.Arrays;

/**
 * ArrayDuplicate.
 * @version 1.0.
 * @since 06.07.2018.
 * @author Rail Shamsemukhametov.
 * */

public class ArrayDuplicate {

    /**
     * remove.
     * Метод , удаляющий дубликаты из массива строк.
     * @param array - исходный массив.
     * @return результат.
     * */

    public String[] remove(String[] array) {
        int unique = array.length;
        for (int out = 0; out < unique; out++) {
            for (int in = out + 1; in < unique; in++) {
                if (array[out].equals(array[in])) {
                    array[out] = array[unique - 1];
                    unique--;
                    in--;
                }
            }
        }
        return Arrays.copyOf(array, unique);
    }
}
