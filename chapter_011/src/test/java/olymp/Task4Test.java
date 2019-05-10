package olymp;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;


public class Task4Test {

    @Test
    public void test1() {
        String input = "a.b...c.";
        String expect = "a.";
        String result = new Task4().calculate(input, 1);
        Assert.assertThat(result, Is.is(expect));
    }

    @Test
    public void test2() {
        String input = "a.b...c.";
        String expect = "b...";
        String result = new Task4().calculate(input, 2);
        Assert.assertThat(result, Is.is(expect));
    }

    @Test
    public void test3() {
        String input = "a.b...c.";
        String expect = "c.";
        String result = new Task4().calculate(input, 3);
        Assert.assertThat(result, Is.is(expect));
    }

}