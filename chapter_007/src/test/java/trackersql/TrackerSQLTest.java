package trackersql;

import org.junit.Test;
import ru.job4j.tracker.Item;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {

//    @Test
//    public void whenAddItemThemReturnThatItem() {
//        TrackerSQL trackerSQL = new TrackerSQL();
//        trackerSQL.cleanTables();
//        String[] string1 = {"1", "2", "3"};
//        Item item1 = new Item("1", "1", "1", 1, string1);
//        assertThat(trackerSQL.add(item1).getId(), is("1"));
//    }
//
//    @Test
//    public void whenDeleteExistItemThenReturnTrue() {
//        TrackerSQL trackerSQL = new TrackerSQL();
//        trackerSQL.cleanTables();
//        String[] string1 = {"10", "20", "30"};
//        Item item1 = new Item("5", "5", "5", 5, string1);
//        trackerSQL.add(item1);
//        assertThat(trackerSQL.delete("5"), is(true));
//    }
//
//    @Test
//    public void whenInvokeFindAllThenReturnsAddedItems() {
//        TrackerSQL trackerSQL = new TrackerSQL();
//        trackerSQL.cleanTables();
//        String[] string1 = {"1", "2", "3"};
//        String[] string2 = {"4", "5", "6"};
//        String[] string3 = {"7", "8", "9"};
//        Item item1 = new Item("1", "1", "1", 1, string1);
//        Item item2 = new Item("2", "2", "2", 2, string2);
//        Item item3 = new Item("3", "3", "3", 3, string3);
//
//        ArrayList<Item> list = new ArrayList<>(Arrays.asList(item1, item2, item3));
//        trackerSQL.add(item1);
//        trackerSQL.add(item2);
//        trackerSQL.add(item3);
//
//        assertThat(list, is(trackerSQL.findAll()));
//    }
//
//    @Test
//    public void whenUpdateThenMustBeUpdated() {
//        TrackerSQL trackerSQL = new TrackerSQL();
//        trackerSQL.cleanTables();
//        String[] string1 = {"1", "2", "3"};
//        String[] string2 = {"4", "5", "6"};
//        Item item1 = new Item("1", "1", "1", 1, string1);
//        Item item2 = new Item("2", "2", "2", 2, string2);
//
//        trackerSQL.add(item1);
//        trackerSQL.replace("1", item2);
//
//        assertThat(item2, is(trackerSQL.findById("1")));
//    }
//
//    @Test
//    public void whenSearchByIdThenMustReturnOneItem() {
//        TrackerSQL trackerSQL = new TrackerSQL();
//        trackerSQL.cleanTables();
//        String[] string1 = {"1", "2", "3"};
//        String[] string2 = {"4", "5", "6"};
//        String[] string3 = {"7", "8", "9"};
//        Item item1 = new Item("1", "1", "1", 1, string1);
//        Item item2 = new Item("2", "2", "2", 2, string2);
//        Item item3 = new Item("3", "3", "3", 3, string3);
//        trackerSQL.add(item1);
//        trackerSQL.add(item2);
//        trackerSQL.add(item3);
//
//        Item result = trackerSQL.findById("3");
//        assertThat(result, is(item3));
//    }
//
//    @Test
//    public void whenSearchByNameThenMustReturnsItemsWithSameName() {
//        TrackerSQL trackerSQL = new TrackerSQL();
//        trackerSQL.cleanTables();
//        String[] string1 = {"1", "2", "3"};
//        String[] string2 = {"4", "5", "6"};
//        String[] string3 = {"7", "8", "9"};
//        Item item1 = new Item("1", "1", "1", 1, string1);
//        Item item2 = new Item("2", "2", "2", 2, string2);
//        Item item3 = new Item("3", "2", "3", 3, string3);
//        trackerSQL.add(item1);
//        trackerSQL.add(item2);
//        trackerSQL.add(item3);
//
//        ArrayList<Item> expected = new ArrayList<>(Arrays.asList(item2, item3));
//        assertThat(trackerSQL.findByName("2"), is(expected));
//    }
//
//    @Test
//    public void whenCloseMethodTest() {
//        try (TrackerSQL trackerSQL = new TrackerSQL()) {
//            trackerSQL.cleanTables();
//            String[] string1 = {"1", "2", "3"};
//            String[] string2 = {"4", "5", "6"};
//            String[] string3 = {"7", "8", "9"};
//            Item item1 = new Item("1", "1", "1", 1, string1);
//            Item item2 = new Item("2", "2", "2", 2, string2);
//            Item item3 = new Item("3", "2", "3", 3, string3);
//            trackerSQL.add(item1);
//            trackerSQL.add(item2);
//            trackerSQL.add(item3);
//        }
//
//    }
}