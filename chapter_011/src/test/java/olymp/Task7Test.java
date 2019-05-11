package olymp;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.TreeMap;

public class Task7Test {

    @Test
    public void test1() {
        int[] data = {3, 4, 2, 3, 1};
        TreeMap<Integer, Pair> expect = new TreeMap<>();
        expect.put(1, new Pair(0, 0));
        expect.put(2, new Pair(0, 0));
        expect.put(3, new Pair(4, 1));
        expect.put(4, new Pair(2, 0));
        Assert.assertThat(new Task7().calculate(data), Is.is(expect));
    }

}