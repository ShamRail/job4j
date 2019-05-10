package olymp;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class Task5Test {

    @Test
    public void test1() {
        Assert.assertThat(new Task5().calculate(2, 3), Is.is(1));
    }

    @Test
    public void test2() {
        Assert.assertThat(new Task5().calculate(9, 5), Is.is(2));
    }

}