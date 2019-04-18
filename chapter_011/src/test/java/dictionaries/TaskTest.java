package dictionaries;

import org.junit.Assert;
import org.junit.Test;


public class TaskTest {

    @Test
    public void whenAllUniqueThenReturn0() {
        int[] data = {1, 2, 3, 4, 5};
        int expected = 0;
        int result = new Task().maxSubtraction(data);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void whenNonUniqueThenReturnUnique() {
        int[] data = {1, 2, 3, 1, 1, 1, 3, 4, 2};
        int expected = 7;
        int result = new Task().maxSubtraction(data);
        Assert.assertEquals(expected, result);
    }
}