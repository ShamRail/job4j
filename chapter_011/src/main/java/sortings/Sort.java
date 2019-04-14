package sortings;

import java.util.Comparator;

public interface Sort {

    <T extends Comparable> void sort(T[] array);

    <T> void sort(T[] array, Comparator<T> comparator);

}
