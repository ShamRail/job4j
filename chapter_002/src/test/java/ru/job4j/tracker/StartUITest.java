package ru.job4j.tracker;

import org.junit.Test;
import org.hamcrest.core.Is;
import org.junit.Assert;
import ru.job4j.*;

public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "7"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        Assert.assertThat(tracker.findAll()[0].getName(), Is.is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("test name", "desc"));
        //создаём StubInput с последовательностью действий(производим замену заявки)
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "7"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        Assert.assertThat(tracker.findById(item.getId()).getName(), Is.is("test replace"));
    }


    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("name1", "desc1"));
        Item item2 = tracker.add(new Item("name2", "desc2"));
        Input input = new StubInput(new String[]{"3", item1.getId(), "7"});
        new StartUI(input, tracker).init();
        Item expect = null;
        Assert.assertThat(tracker.findById(item1.getId()), Is.is(expect));
    }

    @Test
    public void whenFindByID() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("name1", "desc1"));
        Item item2 = tracker.add(new Item("name2", "desc2"));
        Input input = new StubInput(new String[]{"4", item1.getId(), "7"});
        new StartUI(input, tracker).init();
        Assert.assertThat("name2", Is.is(tracker.findById(item2.getId()).getName()));
    }

    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("name1", "desc1"));
        Item item2 = tracker.add(new Item("name2", "desc2"));
        Item item3 = tracker.add(new Item("name1", "desc3"));
        Input input = new StubInput(new String[]{"5", item1.getName(), "7"});
        new StartUI(input, tracker).init();
        Item[] result = tracker.findByName("name1");
        Assert.assertThat(result[0].getDescription() + result[1].getDescription(),
                Is.is("desc1desc3"));
    }

    @Test
    public void whenAddComments() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("123", "name", "desc", 1L, new String[]{"comm1", "comm2"}));
        Input input = new StubInput(new String[]{"6", item.getId(), "comm3", "7"});
        tracker.addCommentById(item.getId(), "comm3");
        new StartUI(input, tracker).init();
        for (Item item1 : tracker.findAll()) {
            for (String string : item1.getComments()) {
                System.out.println(string);
            }
        }
        Assert.assertThat(tracker.findAll()[0].getComments()[2], Is.is("comm3"));
    }
}
