package sortings;

import java.util.Comparator;

public class QuickSort implements Sort {

    @Override
    public <T extends Comparable> void sort(T[] array) {
        quickSort(array, 0, array.length - 1, (o1, o2) -> o1.compareTo(o2));
    }

    @Override
    public <T> void sort(T[] array, Comparator<T> comparator) {
        quickSort(array, 0, array.length - 1, comparator);
    }

    private <T> void quickSort(T[] array, int start, int end, Comparator<T> comparator) {
        if (start < end) {
            int lo = start;
            int hi = end;
            T div = array[(start + end) / 2];
            while (lo <= hi) {
                while (comparator.compare(array[lo], div) < 0) {
                    lo++;
                }
                while (comparator.compare(array[hi], div) > 0) {
                    hi--;
                }
                if (lo <= hi) {
                    T temp = array[lo];
                    array[lo] = array[hi];
                    array[hi] = temp;
                    hi--;
                    lo++;
                }
            }
            quickSort(array, start, lo - 1, comparator);
            quickSort(array, lo, end, comparator);

        }

    }

}
