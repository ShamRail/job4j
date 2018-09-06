package tree;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class SimpleTreeTest {

    private Node<Integer> root = new Node<Integer>(0);
    private SimpleTree<Integer> tree = new SimpleTree<>(root);

    @Before
    public void setUp() {
        Node<Integer> firstLevelFirst = new Node<>(1);
        Node<Integer> firstLevelSecond = new Node<>(2);
        Node<Integer> firstLevelThird = new Node<>(7);

        Node<Integer> secondLevelFirst = new Node<>(3);
        Node<Integer> secondLevelSecond = new Node<>(5);
        Node<Integer> secondLevelThird = new Node<>(6);

        tree.add(root.getValue(), firstLevelFirst.getValue());
        tree.add(root.getValue(), firstLevelSecond.getValue());
        tree.add(root.getValue(), firstLevelThird.getValue());

        tree.add(firstLevelFirst.getValue(), secondLevelFirst.getValue());

        tree.add(firstLevelSecond.getValue(), secondLevelSecond.getValue());
        tree.add(firstLevelSecond.getValue(), secondLevelThird.getValue());
    }

    @Test
    public void whenParentIsNotExistThenAddMethodMustReturnFalse() {
        Assert.assertThat(tree.add(10, 10), Is.is(false));
    }

    @Test
    public void whenParentIsExistThenAddMethodMustReturnTrue() {
        Assert.assertThat(tree.add(7, 8), Is.is(true));
    }

    @Test
    public void whenIterateWithoutConcurrentModifications() {
        Iterator<Integer> treeIterator = tree.iterator();

        Assert.assertThat(treeIterator.hasNext(), Is.is(true));
        Assert.assertThat(treeIterator.next(), Is.is(0));

        Assert.assertThat(treeIterator.hasNext(), Is.is(true));
        Assert.assertThat(treeIterator.next(), Is.is(1));

        Assert.assertThat(treeIterator.hasNext(), Is.is(true));
        Assert.assertThat(treeIterator.next(), Is.is(2));

        Assert.assertThat(treeIterator.hasNext(), Is.is(true));
        Assert.assertThat(treeIterator.next(), Is.is(7));

        Assert.assertThat(treeIterator.hasNext(), Is.is(true));
        Assert.assertThat(treeIterator.next(), Is.is(3));

        Assert.assertThat(treeIterator.hasNext(), Is.is(true));
        Assert.assertThat(treeIterator.next(), Is.is(5));

        Assert.assertThat(treeIterator.hasNext(), Is.is(true));
        Assert.assertThat(treeIterator.next(), Is.is(6));

        Assert.assertThat(treeIterator.hasNext(), Is.is(false));
    }

    @Test
    public void whenExistOnlyRootThenMustNextReturnRoot() {
        tree = new SimpleTree<>(root);

        Iterator<Integer> treeIterator = tree.iterator();

        Assert.assertThat(treeIterator.hasNext(), Is.is(true));
        Assert.assertThat(treeIterator.next(), Is.is(0));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenTreeIsEmptyMustReturnException() {
        tree = new SimpleTree<>(null);

        Iterator<Integer> treeIterator = tree.iterator();

        treeIterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIterateWithConcurrentModificationThenMustBeException() {
        Iterator<Integer> treeIterator = tree.iterator();

        tree.add(7, 8);

        treeIterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAllIterateThenNextInvokeException() {
        Iterator<Integer> treeIterator = tree.iterator();

        for (int i = 0; i < 7; i++) {
            treeIterator.next();
        }

        treeIterator.next();
    }

    @Test
    public void whenAddAllExistElementThenItMustBeIgnoredToAdd() {
        tree.add(0, 2);

        Iterator<Integer> treeIterator = tree.iterator();

        Assert.assertThat(treeIterator.next(), Is.is(0));
        Assert.assertThat(treeIterator.next(), Is.is(1));
        Assert.assertThat(treeIterator.next(), Is.is(2));
        Assert.assertThat(treeIterator.next(), Is.is(7));

        // moving to next level, second "2" was ignored

        Assert.assertThat(treeIterator.next(), Is.is(3));
    }
}