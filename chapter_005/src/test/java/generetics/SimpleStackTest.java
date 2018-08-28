package generetics;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleStackTest {

    SimpleStack<String> names = new SimpleStack<>();

    @Before
    public void setup() {
        names.push("Petr");
        names.push("Sidor");
        names.push("Alex");
    }

    @Test
    public void whenTestPollMethod() {
        Assert.assertThat(names.poll(), Is.is("Alex"));
        Assert.assertThat(names.poll(), Is.is("Sidor"));
        Assert.assertThat(names.poll(), Is.is("Petr"));
    }

    @Test
    public void whenStackEmptyThenReturnNull() {
        names = new SimpleStack<>();
        Assert.assertThat(names.poll(), Is.is((String) null));
    }
}