package sortings;

import java.util.Comparator;
import java.util.function.BiPredicate;

public class HeapSort implements Sort {

    @Override
    public <T extends Comparable> void sort(T[] array) {
        heapSort(array, (el1, el2) -> el1.compareTo(el2) >= 0);
    }

    @Override
    public <T> void sort(T[] array, Comparator<T> comparator) {
        heapSort(array, (el1, el2) -> comparator.compare(el1, el2) >= 0);
    }

    private <T> void heapSort(T[] array, BiPredicate<T, T> predicate) {
        buildHeap(array, predicate);
        for (int i = 0; i < array.length; i++) {
            T top = removeTop(array, predicate, array.length - i);
            array[array.length - 1 - i] = top;
        }
    }

    private <T> void buildHeap(T[] array, BiPredicate<T, T> condition) {
        restoreHeap(array, condition, array.length);
    }

    private <T> void restoreHeap(T[] array, BiPredicate<T, T> condition, int size) {
        for (int i = size - 1; i >= size / 2; i--) {
            for (int node = i; node >= 1; node--) {
                int parent = (node - 1) / 2;
                if (condition.test(array[node], array[parent])) {
                    T temp = array[parent];
                    array[parent] = array[node];
                    array[node] = temp;
                }
            }
        }
    }

    private <T> T removeTop(T[] array, BiPredicate<T, T> condition, int size) {
        T top = array[0];
        array[0] = array[size - 1];
        restoreHeap(array, condition, size - 1);
        return top;
    }

}
