package sortings;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;


public class HeapSortTest {

    @Test
    public void whenTestDefaultSortingMethod() {
        Sort sort = new HeapSort();
        Integer[] data = {3, 2, 1};
        sort.sort(data);
        Integer[] result = {1, 2, 3};
        Assert.assertThat(data, Is.is(result));
    }

    @Test
    public void whenSortUsingComparator() {
        Sort sort = new HeapSort();
        Integer[] data = {3, 2, 1};
        sort.sort(data, Integer::compareTo);
        Integer[] result = {1, 2, 3};
        Assert.assertThat(data, Is.is(result));
    }

    @Test
    public void sortWithTenNumbers() {
        Sort sort = new HeapSort();
        Integer[] data = {5, 4, 3, 2, 1};
        sort.sort(data, Integer::compareTo);
        Integer[] result = {1, 2, 3, 4, 5};
        Assert.assertThat(data, Is.is(result));
    }

    @Test
    public void test() {
        Sort sort = new HeapSort();
        Integer[] data = {6, 7, 5, 9, 5, 3, 8, 4, 4, 0, 3, 6};
        sort.sort(data, Integer::compareTo);
        Integer[] result = {0, 3, 3, 4, 4, 5, 5, 6, 6, 7, 8, 9};
        Assert.assertThat(data, Is.is(result));
    }

    @Test
    public void test2() {
        Sort sort = new HeapSort();
        Integer[] data = {6, 7, 5, 9, 5, 0, 0};
        sort.sort(data, Integer::compareTo);
        Integer[] result = {0, 0, 5, 5, 6, 7, 9};
        Assert.assertThat(data, Is.is(result));
    }

}