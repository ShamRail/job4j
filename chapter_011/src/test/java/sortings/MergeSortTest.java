package sortings;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class MergeSortTest {

    @Test
    public void whenTestDefaultSortingMethod() {
        Sort sort = new MergeSort();
        Integer[] data = {3, 2, 1};
        sort.sort(data);
        Integer[] result = {1, 2, 3};
        Assert.assertThat(data, Is.is(result));
    }

    @Test
    public void whenSortUsingComparator() {
        Sort sort = new MergeSort();
        Integer[] data = {3, 2, 1};
        sort.sort(data, Integer::compareTo);
        Integer[] result = {1, 2, 3};
        Assert.assertThat(data, Is.is(result));
    }

    @Test
    public void sortWithTenNumbers() {
        Sort sort = new MergeSort();
        Integer[] data = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort.sort(data, Integer::compareTo);
        Integer[] result = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Assert.assertThat(data, Is.is(result));
    }

}