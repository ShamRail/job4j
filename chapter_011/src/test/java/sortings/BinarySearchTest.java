package sortings;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTest {

    @Test
    public void whenContainsValueThenReturnItIndex() {
        Integer[] data = {1, 2, 3, 4, 5};
        BinarySearch binarySearch = new BinarySearch(data);
        int expected = 2;
        int result = binarySearch.findIndex(3);
        Assert.assertThat(expected, Is.is(result));
    }

    @Test
    public void whenNotContainsValueThenReturnItIndex() {
        Integer[] data = {1, 2, 3, 4, 5};
        BinarySearch binarySearch = new BinarySearch(data);
        int expected = -1;
        int result = binarySearch.findIndex(6);
        Assert.assertThat(expected, Is.is(result));
    }

}