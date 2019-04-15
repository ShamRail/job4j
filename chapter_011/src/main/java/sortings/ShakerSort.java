package sortings;

import java.util.Comparator;
import java.util.function.BiPredicate;

public class ShakerSort implements Sort {

    @Override
    public <T extends Comparable> void sort(T[] array) {
        cycle(array, (el1, el2) -> el1.compareTo(el2) > 0);
    }

    @Override
    public <T> void sort(T[] array, Comparator<T> comparator) {
        cycle(array, (el1, el2) -> comparator.compare(el1, el2) > 0);
    }

    private <T> void cycle(T[] array, BiPredicate<T, T> condition) {
        int left = 0;
        int right = array.length - 1;
        int flag = 1;
        while ((left < right) && flag > 0) {
            flag = 0;
            for (int i = left; i < right; i++) {
                if (condition.test(array[i], array[i + 1])) {
                    T temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    flag = 1;
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                if (condition.test(array[i - 1], array[i])) {
                    T temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                    flag = 1;
                }
            }
            left++;
        }
    }
}
