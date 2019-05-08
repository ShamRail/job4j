package olymp;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class Task3Test {

    @Test
    public void test1() {
        int[] input = {2};
        List<Integer> result = new Task3().calculate(input);
        List<Integer> expect = Arrays.asList(1, 2);
        Assert.assertThat(result, Is.is(expect));
    }

    @Test
    public void test2() {
        int[] input = {2, 2};
        List<Integer> result = new Task3().calculate(input);
        List<Integer> expect = Arrays.asList(2, 2);
        Assert.assertThat(result, Is.is(expect));
    }

    @Test
    public void test3() {
        int[] input = {10, 20, 20};
        List<Integer> result = new Task3().calculate(input);
        List<Integer> expect = Arrays.asList(1, 10, 2, 20);
        Assert.assertThat(result, Is.is(expect));
    }

    @Test
    public void test4() {
        int[] input = {6, 6, 2, 2, 2, 2, 1, 2, 3, 3, 3, 3};
        List<Integer> result = new Task3().calculate(input);
        List<Integer> expect = Arrays.asList(2, 6, 4, 2, 1, 1, 1, 2, 4, 3);
        Assert.assertThat(result, Is.is(expect));
    }

}