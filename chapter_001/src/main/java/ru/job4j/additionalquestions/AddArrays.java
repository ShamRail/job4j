package ru.job4j.additionalquestions;

/**
 * Segments.
 * @version 1.0
 * @since 05.07.2018
 * @author Rail Shamsemuhametov.
 */

public class AddArrays {
    private int[] arr1;
    private int[] arr2;

    public AddArrays(int[] arr1, int[] arr2) {
        this.arr1 = arr1;
        this.arr2 = arr2;
    }

    /**
     * addArrays.
     * Метод, сливающий два отсортированных массива в один.
     * Два последних цикла работают в случае если исходные массивы разной длины.
     * @return - результирующий массив.
     * */

    public int[] addArrays() {
        int[] result = new int[arr1.length + arr2.length];
        int stepByArr1 = 0, stepByArr2 = 0, stepByResult = 0;
        while (stepByArr1 < arr1.length && stepByArr2 < arr2.length) {
            if (arr1[stepByArr1] < arr2[stepByArr2]) {
                result[stepByResult] = arr1[stepByArr1];
                stepByArr1++;
            } else {
                result[stepByResult] = arr2[stepByArr2];
                stepByArr2++;
            }
            stepByResult++;
        }
        while (stepByArr1 < arr1.length) {
            result[stepByResult] = arr1[stepByArr1];
            stepByArr1++;
            stepByResult++;
        }
        while (stepByArr2 < arr1.length) {
            result[stepByResult] = arr2[stepByArr2];
            stepByArr2++;
            stepByResult++;
        }
        return result;
    }
}
