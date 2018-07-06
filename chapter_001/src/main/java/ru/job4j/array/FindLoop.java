package ru.job4j.array;

/**
 * FindLoop.
 * @version 1.0.
 * @since 06.07.2018.
 * @author Rail Shamsemukhametov.
 * */

public class FindLoop {

    /**
     * indexOf.
     * Метод , ищущий индекс элемента , содержащегося в массиве
     * если его нет возвращает -1
     * @param el - значение искомого элемента , находящегося по индексу
     * @return индекс
     * */

    public int indexOf(int[] data, int el) {
        int rst = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == el) {
                rst = i;
                break;
            }
        }
        return rst;
    }
}