package olymp;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class Task1Test {

    @Test
    public void whenP4AndK1ThenRes1() {
        Assert.assertThat(new Task1().calculate(4, 1), Is.is(1));
    }

    @Test
    public void whenP6AndK2ThenRes2() {
        Assert.assertThat(new Task1().calculate(6, 2), Is.is(2));
    }

}