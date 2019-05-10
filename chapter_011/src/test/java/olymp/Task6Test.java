package olymp;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class Task6Test {

    @Test
    public void test1() {
        int[] data = {50, 100, 50, 50};
        Assert.assertThat(new Task6().calculate(data), Is.is(2));
    }

    @Test
    public void test2() {
        int[] data = {100, 100, 100};
        Assert.assertThat(new Task6().calculate(data), Is.is(3));
    }

}