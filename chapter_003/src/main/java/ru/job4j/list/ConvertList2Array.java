package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int[][] array = new int[rows][rows];
        for (Integer value : list) {
            array[list.indexOf(value) / rows][list.indexOf(value) % rows] = value;
        }
        if (list.size() % rows != 0) {
            for (int i = list.size(); i < rows*rows; i++) {
                array[i / rows][i % rows] = 0;
            }
        }
        return array;
    }

    public List<Integer> convert (List<int[]> list) {
        List<Integer> result = new ArrayList<Integer>();
        for (int[] array : list) {
            for (int element : array) {
                result.add(element);
            }
        }
        return result;
    }
}