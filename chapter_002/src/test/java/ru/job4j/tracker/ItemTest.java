package ru.job4j.tracker;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.tracker.Item;


public class ItemTest {
    @Test
    public void whenSetId123ThenIdMustBe123() {
        Item item = new Item();
        item.setId("123");
        Assert.assertThat(item.getId(), Is.is("123"));
    }

    @Test
    public void whenSetNameRailThenNameMustBeRail() {
        Item item = new Item();
        item.setName("Rail");
        Assert.assertThat(item.getName(), Is.is("Rail"));
    }

    @Test
    public void whenSetDescriptionDescThenDescriptionDesc() {
        Item item = new Item();
        item.setDescription("desc");
        Assert.assertThat(item.getDescription(), Is.is("desc"));
    }

    @Test
    public void whenSetCommentCommThenCommentComm() {
        Item item = new Item();
        item.setComments("comm");
        Assert.assertThat(item.getComments().get(0), Is.is("comm"));
    }
    @Test
    public void whenSetCreate123ThenCreate123() {
        Item item = new Item();
        item.setCreate(123);
        Assert.assertThat(item.getCreate(), Is.is(123L));
    }
}
