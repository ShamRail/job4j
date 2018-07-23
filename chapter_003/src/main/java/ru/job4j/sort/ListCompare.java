package ru.job4j.sort;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        char[] leftAsArray = left.toCharArray();
        char[] rightAsArray = right.toCharArray();
        int lenght = left.length() < right.length() ? left.length() : right.length();
        int resultOfCompareChars = 0;
        for (int i = 0; i < lenght; i++) {
            resultOfCompareChars = Character.compare(leftAsArray[i], rightAsArray[i]);
            if (resultOfCompareChars != 0) {
                break;
            }
        }
        return resultOfCompareChars != 0 ? resultOfCompareChars : left.length() - right.length();
    }
}
