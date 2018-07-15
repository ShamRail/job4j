package ru.job4j.tracker;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.Item;
import ru.job4j.Tracker;


public class TrackerTest {
    @Test
    public void whenAddItemThenItem() {
        Tracker tracker = new Tracker();
        Item expect = new Item("123", "Name",
                "Desc", 123L, new String[] {"comm"});
        expect.setId("123");
        Assert.assertThat(tracker.add(expect), Is.is(expect));
    }

    @Test
    public void whenAddThreeItemsAndDelOneItemThenTwoItem() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("123", "Name",
                "Desc", 123L, new String[] {"comm"}));
        tracker.add(new Item("1231", "Name1",
                "Desc1", 1231L, new String[] {"comm1"}));
        tracker.add(new Item("1232", "Name2",
                "Desc2", 123L, new String[] {"comm2"}));
        String id = tracker.findAll()[0].getId();
        tracker.delete(id);
        Assert.assertThat(tracker.findAll()[0].getCreate() + tracker.findAll()[1].getCreate(),
                Is.is(1354L));
    }
    @Test
    public void whenReplaceOneItemThenItChanges() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("123", "Name",
                "Desc", 123L, new String[] {"comm"}));
        tracker.add(new Item("1231", "Name1",
                "Desc1", 1231L, new String[] {"comm1"}));
        tracker.add(new Item("1232", "Name2",
                "Desc2", 123L, new String[] {"comm2"}));
        String id = tracker.findAll()[1].getId();
        Item newitem = new Item("new id", "new name", "new desc",
                123L, new String[] {"new comment"});
        tracker.replace(id, newitem);
        Assert.assertThat(tracker.findAll()[1].getName(), Is.is("new name"));
    }
    @Test
    public void whenFindAllThenMustBeHowCreated() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("123", "Name",
                "Desc", 123L, new String[] {"comm"}));
        tracker.add(new Item("1231", "Name1",
                "Desc1", 1231L, new String[] {"comm1"}));
        Item[] expect = {
                new Item("123", "Name",
                        "Desc", 123L, new String[] {"comm"}),
                new Item("1231", "Name1",
                        "Desc1", 1231L, new String[] {"comm1"})
        };
        Assert.assertThat(tracker.findAll()[0].getCreate() + tracker.findAll()[1].getCreate(),
                Is.is(1354L));
    }

    @Test
    public void whenItemsIsEmptyThenFindByNameReturnEmptyArray() {
        Tracker tracker = new Tracker();
        Item expect = null;
        Assert.assertThat(tracker.findByName("Name"), Is.is(expect));
    }

    @Test
    public void whenItemsIsNotEmpyThenFindByNameReturnItemsSameName() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("123", "Name",
                "Desc", 123L, new String[] {"comm"}));
        tracker.add(new Item("1231", "Name1",
                "Desc1", 1231L, new String[] {"comm1"}));
        tracker.add(new Item("123", "Name",
                "Desc", 123L, new String[] {"comm2"}));
        Item[] result = tracker.findByName("Name");
        Assert.assertThat(result[0].getCreate() + result[1].getCreate(),
                Is.is(246L));
    }

    @Test
    public void whenItemsIsEmptyThenFindByIdReturnNull() {
        Tracker tracker = new Tracker();
        Item expect = null;
        Assert.assertThat(tracker.findById("123"), Is.is(expect));
    }

    @Test
    public void whenItemsIsNotEmpyThenFindByIdReturnItemContainedId() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("123", "Name",
                "Desc", 123L, new String[] {"comm"}));
        tracker.add(new Item("1231", "Name1",
                "Desc1", 1231L, new String[] {"comm1"}));
        tracker.add(new Item("1232", "Name2",
                "Desc2", 123L, new String[] {"comm2"}));
        String id = tracker.findAll()[1].getId();
        Item result  = tracker.findById(id);
        Assert.assertThat(result.getName(), Is.is("Name1"));
    }
}
