package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.hamcrest.core.Is;
import org.junit.Assert;
import ru.job4j.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;

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

    // поле содержит дефолтный вывод в консоль.
    private final PrintStream stdout = System.out;
    // буфер для результата.
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.out.println("execute after method");
        System.setOut(this.stdout);
    }

    @Test
    public void whenShowItems() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("name1", "desc1"));
        Item item2 = tracker.add(new Item("name2", "desc2"));
        Item item3 = tracker.add(new Item("name3", "desc3"));
        Input input = new StubInput(new String[]{"1", "7"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(this.out.toByteArray()), Is.is(this.toString(item1.getId(), item2.getId(), item3.getId())));

    }
    private String toString(String id1, String id2, String id3) {
        String s = System.lineSeparator();
        String menu = String.format("Меню.%sВыберите пункт меню :%s0.Добавить новую заявку%s1.Показать все заявки%s2.Редактировать заявку%s3.Удалить заявку%s4.Найти заявку по id%s5.Найти заявки по имени%s6.Добавить комментарий по id%s7.Выйти%s",
                s, s, s, s, s, s, s, s, s, s);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(menu);
        stringBuilder
                .append(String.format("------------ Все заявки --------------%s", s))
                .append(String.format("идентификатор : %s, имя : name1, описание заявки : desc1%s", id1, s))
                .append(String.format("идентификатор : %s, имя : name2, описание заявки : desc2%s", id2, s))
                .append(String.format("идентификатор : %s, имя : name3, описание заявки : desc3%s", id3, s));
        stringBuilder.append(menu);
        return stringBuilder.toString();
    }
}
