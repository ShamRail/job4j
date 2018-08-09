package ru.job4j.list;

import java.util.*;


public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int[][] array = new int[rows][rows];
        list.forEach((value) -> array[list.indexOf(value) / rows][list.indexOf(value) % rows] = value);
        if (list.size() % rows != 0) {
            for (int i = list.size(); i < rows * rows; i++) {
                array[i / rows][i % rows] = 0;
            }
        }
        return array;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<Integer>();
        list.stream().flatMapToInt(Arrays::stream).forEach(result::add);
        return result;
    }
}