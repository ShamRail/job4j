package sortings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiPredicate;

public class MergeSort implements Sort {


    @Override
    public <T extends Comparable> void sort(T[] array) {
        mergeSort(array, 0, array.length - 1, (el1, el2) -> el1.compareTo(el2) < 0);
    }

    @Override
    public <T> void sort(T[] array, Comparator<T> comparator) {
        mergeSort(array, 0, array.length - 1, (el1, el2) -> comparator.compare(el1, el2) < 0);
    }

    /**
     * Сортируем левую часть, потом правую.
     * После проводим слияние.
     * [si, mi] + [mi + 1, ei], где mi индекс середины промежутка.
     * @param array сортируемый массив
     * @param si индекс начало промежетка
     * @param ei индекс конца промежутка
     * @param condition условие сортировки
     * */

    private  <T> void mergeSort(T[] array, int si, int ei, BiPredicate<T, T> condition) {
        if (si < ei) {
            int mi = (si + ei) / 2;
            mergeSort(array, si, mi, condition);
            mergeSort(array, mi + 1, ei, condition);
            merge(array, si, mi, ei, condition);
        }
    }

    private <T> void merge(T[] array, int si, int mi, int ei, BiPredicate<T, T> condition) {
        T[] leftArray = Arrays.copyOfRange(array, si, mi + 1);
        T[] rightArray = Arrays.copyOfRange(array, mi + 1, ei + 1);
        int li = 0;
        int ri = 0;
        int ai = si;
        while (li < leftArray.length && ri < rightArray.length) {
            array[ai++] = condition.test(leftArray[li], rightArray[ri]) ? leftArray[li++] : rightArray[ri++];
        }
        if (li < leftArray.length) {
            System.arraycopy(leftArray, li, array, ai, leftArray.length - li);
        }
        if (ri < rightArray.length) {
            System.arraycopy(rightArray, ri, array, ai, rightArray.length - ri);
        }
    }

}
