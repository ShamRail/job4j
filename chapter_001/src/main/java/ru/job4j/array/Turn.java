package ru.job4j.array;

/**
 * Turn.
 * @version 1.0.
 * @since 06.07.2018.
 * @author Rail Shamsemukhametov.
 * */

public class Turn {

    /**
     * turn.
     * Метод, переваращивающий массив.
     * @param array - исходный массив
     * @return перевернутый массив
     */

    public int[] turn(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }

}