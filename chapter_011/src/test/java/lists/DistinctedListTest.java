package lists;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class DistinctedListTest {

    @Test
    public void solution1Test() {
        LinkedList<Integer> data = new LinkedList<>(Arrays.asList(1, 2, 3, 1, 2, 3, 4, 5, 6, 6, 7, 8, 7));
        DistinctedList distinctedList = new DistinctedList();
        distinctedList.solution1(data);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        Assert.assertThat(data, Is.is(expected));
    }

    @Test
    public void solution2Test() {
        LinkedList<Integer> data = new LinkedList<>(Arrays.asList(1, 2, 3, 1, 2, 3, 4, 5, 6, 6, 7, 8, 7));
        DistinctedList distinctedList = new DistinctedList();
        List<Integer> result = distinctedList.solution2(data);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        Assert.assertThat(result, Is.is(expected));
    }
}