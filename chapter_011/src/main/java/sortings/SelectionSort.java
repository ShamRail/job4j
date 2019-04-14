package sortings;

import java.util.Comparator;
import java.util.function.BiPredicate;

public class SelectionSort implements Sort {

    private <T> void cycle(T[] array, BiPredicate<T, T> condition) {
        for (int i = 0; i < array.length - 1; i++) {
            T min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (condition.test(min, array[j])) {
                    min = array[j];
                    minIndex = j;
                }
            }
            T temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    @Override
    public <T extends Comparable> void sort(T[] array) {
        cycle(array, (el1, el2) -> el1.compareTo(el2) > 0);
    }

    @Override
    public <T> void sort(T[] array, Comparator<T> comparator) {
        cycle(array, (el1, el2) -> comparator.compare(el1, el2) > 0);
    }
}
