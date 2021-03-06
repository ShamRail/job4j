package sortings;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class ShakerSortTest {

    @Test
    public void whenTestDefaultSortingMethod() {
        Sort sort = new ShakerSort();
        Integer[] data = {3, 2, 1};
        sort.sort(data);
        Integer[] result = {1, 2, 3};
        Assert.assertThat(data, Is.is(result));
    }

    @Test
    public void whenSortUsingComparator() {
        Sort sort = new ShakerSort();
        Integer[] data = {3, 2, 1};
        sort.sort(data, Integer::compareTo);
        Integer[] result = {1, 2, 3};
        Assert.assertThat(data, Is.is(result));
    }
}