package set;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class SimpleSetTest {

    SimpleSet<String> cities = new SimpleSet<>();

    @Before
    public void setUp() {
        cities.add("Moscow");
        cities.add("Ufa");
        cities.add("Sant-Petersburg");
    }

    @Test
    public void whenTryAddExistElementThenItWontAdd() {
        int sizeBefore = cities.size();
        cities.add("Ufa");
        Assert.assertThat(cities.size(), Is.is(sizeBefore));
    }

    @Test
    public void whenTryAddNotExistElementThenItAdd() {
        int sizeBefore = cities.size();
        cities.add("Volgograd");
        Assert.assertThat(cities.size(), Is.is(sizeBefore + 1));
        Iterator<String> integerIterator = cities.iterator();
        Assert.assertThat(integerIterator.next(), Is.is("Moscow"));
        Assert.assertThat(integerIterator.next(), Is.is("Ufa"));
        Assert.assertThat(integerIterator.next(), Is.is("Sant-Petersburg"));
        Assert.assertThat(integerIterator.next(), Is.is("Volgograd"));
    }

    @Test
    public void whenSetDoesntContainsThenFalse() {
        Assert.assertThat(cities.contains("Kazan"), Is.is(false));
    }

    @Test
    public void whenSetContainsThenTrue() {
        Assert.assertThat(cities.contains("Ufa"), Is.is(true));
    }
}