package olymp;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class Task2Test {

    @Test
    public void whenA140B10C170ThenResult10And10() {
        Assert.assertThat(new Task2().calculate(140, 10, 170), Is.is(new Pair(10, 10)));
    }

    @Test
    public void whenA10B10C21ThenResult1And0() {
        Assert.assertThat(new Task2().calculate(10, 10, 21), Is.is(new Pair(1, 0)));
    }

}