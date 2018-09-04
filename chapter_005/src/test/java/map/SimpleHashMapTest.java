package map;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class SimpleHashMapTest {

    SimpleHashMap<String, Integer> map;

    @Before
    public void setUp() {
        map = new SimpleHashMap<>();
    }

    @Test
    public void whenCreateSize0() {
        Assert.assertThat(map.size(), Is.is(0));
    }

    @Test
    public void whenAddThenSizeIncrease() {
        map.insert("123", 1);
        Assert.assertThat(map.size(), Is.is(1));
    }

    @Test
    public void whenAddNullThenMustPutItOnZeroPositionAndMustReturnValue() {
        map.insert(null, 1);
        Assert.assertThat(map.get(null), Is.is(1));
    }

    @Test
    public void whenAddNullKeyAndNullValueThenGetNullValue() {
        map.insert(null, null);
        Assert.assertThat(map.get(null), Is.is((Integer) null));
    }

    @Test
    public void whenHashcodesEqualsButValuesIsDifferentThenValuesMustReplaced() {
        map.insert("123", 1);
        map.insert("123", 2);
        Assert.assertThat(map.get("123"), Is.is(2));
    }

    @Test
    public void whenParamOfGetMethodIsNotExistThenMustReturnNull() {
        Assert.assertThat(map.get("123"), Is.is((Integer) null));
    }

    @Test
    public void whenOccurCollisionThenGetMethodReturnExpectedValue() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert(1, "one");
        simpleHashMap.insert(17, "seventeen");
        Assert.assertThat(simpleHashMap.get(1), Is.is("one"));
        Assert.assertThat(simpleHashMap.get(17), Is.is("seventeen"));
    }

    @Test
    public void whenTableFillOutThenMustResize() {
        SimpleHashMap<Integer, Integer> simpleHashMap = new SimpleHashMap<>();

        for (int i = 0; i < 16; i++) {
            simpleHashMap.insert(i, i);
        }

        simpleHashMap.insert(16, 16);

        for (int i = 0; i < 17; i++) {
            Assert.assertThat(simpleHashMap.get(i), Is.is(i));
        }
    }

    @Test
    public void whenTableWithCollisionFillOutThenMustResizeAndOverWriteCollisionPlaces() {
        SimpleHashMap<Integer, Integer> simpleHashMap = new SimpleHashMap<>();

        simpleHashMap.insert(null, null);
        simpleHashMap.insert(17, 17);
        simpleHashMap.insert(33, 33);

        for (int i = 0; i < 14; i++) {
            simpleHashMap.insert(i, i);
        }
        simpleHashMap.insert(16, 16);

        for (int i = 0; i < 14; i++) {
            Assert.assertThat(simpleHashMap.get(i), Is.is(i));
        }

        Assert.assertThat(simpleHashMap.get(null), Is.is((Integer) null));
        Assert.assertThat(simpleHashMap.get(17), Is.is(17));
        Assert.assertThat(simpleHashMap.get(33), Is.is(33));
        Assert.assertThat(simpleHashMap.get(16), Is.is(16));
    }

    @Test
    public void whenAllInListBucketThenMustReturnAllValues() {
        SimpleHashMap<Integer, Integer> simpleHashMap = new SimpleHashMap<>();

        simpleHashMap.insert(1, 1);
        simpleHashMap.insert(17, 17);
        simpleHashMap.insert(33, 33);
        simpleHashMap.insert(65, 65);

        Assert.assertThat(simpleHashMap.get(1), Is.is(1));
        Assert.assertThat(simpleHashMap.get(17), Is.is(17));
        Assert.assertThat(simpleHashMap.get(33), Is.is(33));
        Assert.assertThat(simpleHashMap.get(65), Is.is(65));

    }

    @Test
    public void whenDeleteWithoutCollision() {
        SimpleHashMap<Integer, Integer> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert(1, 1);
        simpleHashMap.insert(2, 2);

        simpleHashMap.delete(1);
        simpleHashMap.delete(2);

        Assert.assertThat(simpleHashMap.size(), Is.is(0));
        Assert.assertThat(simpleHashMap.get(1), Is.is((Integer) null));
        Assert.assertThat(simpleHashMap.get(2), Is.is((Integer) null));

    }

    @Test
    public void whenDeleteWithCollision() {
        SimpleHashMap<Integer, Integer> simpleHashMap = new SimpleHashMap<>();

        simpleHashMap.insert(1, 1);
        simpleHashMap.insert(17, 17);
        simpleHashMap.insert(33, 33);

        simpleHashMap.delete(1);
        simpleHashMap.delete(17);
        simpleHashMap.delete(33);

        Assert.assertThat(simpleHashMap.size(), Is.is(0));
        Assert.assertThat(simpleHashMap.get(1), Is.is((Integer) null));
        Assert.assertThat(simpleHashMap.get(17), Is.is((Integer) null));
        Assert.assertThat(simpleHashMap.get(33), Is.is((Integer) null));
    }

    @Test
    public void whenDeleteOneElementWithoutCollisionAndOneElementWithCollision() {
        SimpleHashMap<Integer, Integer> simpleHashMap = new SimpleHashMap<>();

        simpleHashMap.insert(null, null);
        simpleHashMap.insert(0, 0);
        simpleHashMap.insert(2, 2);

        simpleHashMap.delete(0);
        simpleHashMap.delete(null);
        simpleHashMap.delete(2);

        Assert.assertThat(simpleHashMap.size(), Is.is(0));
        Assert.assertThat(simpleHashMap.get(null), Is.is((Integer) null));
        Assert.assertThat(simpleHashMap.get(0), Is.is((Integer) null));
        Assert.assertThat(simpleHashMap.get(2), Is.is((Integer) null));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModifiedWhenIterateThenMustBeException() {
        SimpleHashMap<Integer, Integer> simpleHashMap = new SimpleHashMap<>();

        simpleHashMap.insert(1, 1);
        simpleHashMap.insert(2, 2);
        simpleHashMap.insert(3, 3);

        Iterator<SimpleHashMap.Entry<Integer, Integer>> iterator = simpleHashMap.iterator();
        simpleHashMap.insert(4, 4);
        iterator.next();

    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateWithNonExistElementsThenMustBeException() {
        SimpleHashMap<Integer, Integer> simpleHashMap = new SimpleHashMap<>();

        Iterator<SimpleHashMap.Entry<Integer, Integer>> iterator = simpleHashMap.iterator();

        iterator.next();
    }

    @Test
    public void whenIterateWithoutCollision() {
        SimpleHashMap<Integer, Integer> simpleHashMap = new SimpleHashMap<>();

        simpleHashMap.insert(1, 1);
        simpleHashMap.insert(2, 4);
        simpleHashMap.insert(3, 9);
        simpleHashMap.insert(4, 16);
        simpleHashMap.insert(5, 25);

        Iterator<SimpleHashMap.Entry<Integer, Integer>> iterator = simpleHashMap.iterator();

        Assert.assertThat(iterator.hasNext(), Is.is(true));
        Assert.assertThat(iterator.next().value, Is.is(1));

        Assert.assertThat(iterator.hasNext(), Is.is(true));
        Assert.assertThat(iterator.next().value, Is.is(4));

        Assert.assertThat(iterator.hasNext(), Is.is(true));
        Assert.assertThat(iterator.next().value, Is.is(9));

        Assert.assertThat(iterator.hasNext(), Is.is(true));
        Assert.assertThat(iterator.next().value, Is.is(16));

        Assert.assertThat(iterator.hasNext(), Is.is(true));
        Assert.assertThat(iterator.next().value, Is.is(25));

        Assert.assertThat(iterator.hasNext(), Is.is(false));
    }

    @Test
    public void whenIterateWithCollision() {
        SimpleHashMap<Integer, Integer> simpleHashMap = new SimpleHashMap<>();

        simpleHashMap.insert(null, null);
        simpleHashMap.insert(0, 0);
        simpleHashMap.insert(1, 1);
        simpleHashMap.insert(17, 17);
        simpleHashMap.insert(15, 15);

        Iterator<SimpleHashMap.Entry<Integer, Integer>> iterator = simpleHashMap.iterator();

        Assert.assertThat(iterator.hasNext(), Is.is(true));
        Assert.assertThat(iterator.next().value, Is.is(0));

        Assert.assertThat(iterator.hasNext(), Is.is(true));
        Assert.assertThat(iterator.next().value, Is.is((Integer) null));

        Assert.assertThat(iterator.hasNext(), Is.is(true));
        Assert.assertThat(iterator.next().value, Is.is(17));

        Assert.assertThat(iterator.hasNext(), Is.is(true));
        Assert.assertThat(iterator.next().value, Is.is(1));

        Assert.assertThat(iterator.hasNext(), Is.is(true));
        Assert.assertThat(iterator.next().value, Is.is(15));

        Assert.assertThat(iterator.hasNext(), Is.is(false));

    }
}