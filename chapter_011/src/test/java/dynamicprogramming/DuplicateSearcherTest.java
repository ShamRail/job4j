package dynamicprogramming;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class DuplicateSearcherTest {

    @Test
    public void whenNonDuplicate() {
        Integer[] data = {1, 2, 3};
        Assert.assertThat(new DuplicateSearcher().search(data), Is.is(0));
    }

    @Test
    public void whenContainsDuplicate() {
        Integer[] data = {1, 1, 2, 3};
        Assert.assertThat(new DuplicateSearcher().search(data), Is.is(1));
    }

    @Test
    public void whenContainsTwoDuplicate() {
        Integer[] data = {1, 1, 2, 3, 3};
        Assert.assertThat(new DuplicateSearcher().search(data), Is.is(2));
    }
}