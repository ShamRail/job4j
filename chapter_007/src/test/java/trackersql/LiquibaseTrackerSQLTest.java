package trackersql;

import org.hamcrest.Matchers;
import org.junit.Test;
import ru.job4j.tracker.Item;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class LiquibaseTrackerSQLTest {

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("tracker_url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenAddItemThemReturnThatItem() throws SQLException {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            String[] string1 = {"1", "2", "3"};
            Item item1 = new Item("1", "1", "1", 1, string1);
            assertThat(trackerSQL.add(item1).getId(), Matchers.is("1"));
        }
    }

    @Test
    public void whenDeleteExistItemThenReturnTrue() throws SQLException {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            String[] string1 = {"10", "20", "30"};
            Item item1 = new Item("5", "5", "5", 5, string1);
            trackerSQL.add(item1);
            assertThat(trackerSQL.delete("5"), is(true));
        }
    }

    @Test
    public void whenInvokeFindAllThenReturnsAddedItems() throws SQLException {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            String[] string1 = {"1", "2", "3"};
            String[] string2 = {"4", "5", "6"};
            String[] string3 = {"7", "8", "9"};
            Item item1 = new Item("1", "1", "1", 1, string1);
            Item item2 = new Item("2", "2", "2", 2, string2);
            Item item3 = new Item("3", "3", "3", 3, string3);
            ArrayList<Item> list = new ArrayList<>(Arrays.asList(item1, item2, item3));
            trackerSQL.add(item1);
            trackerSQL.add(item2);
            trackerSQL.add(item3);
            assertThat(list, is(trackerSQL.findAll()));
        }
    }

    @Test
    public void whenUpdateThenMustBeUpdated() throws SQLException {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            String[] string1 = {"1", "2", "3"};
            String[] string2 = {"4", "5", "6"};
            Item item1 = new Item("1", "1", "1", 1, string1);
            Item item2 = new Item("2", "2", "2", 2, string2);
            trackerSQL.add(item1);
            trackerSQL.replace("1", item2);
            assertThat(item2, is(trackerSQL.findById("1")));
        }
    }


    @Test
    public void whenSearchByIdThenMustReturnOneItem() throws SQLException {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            String[] string1 = {"1", "2", "3"};
            String[] string2 = {"4", "5", "6"};
            String[] string3 = {"7", "8", "9"};
            Item item1 = new Item("1", "1", "1", 1, string1);
            Item item2 = new Item("2", "2", "2", 2, string2);
            Item item3 = new Item("3", "3", "3", 3, string3);
            trackerSQL.add(item1);
            trackerSQL.add(item2);
            trackerSQL.add(item3);
            Item result = trackerSQL.findById("3");
            assertThat(result, is(item3));
        }
    }

    @Test
    public void whenSearchByNameThenMustReturnsItemsWithSameName() throws SQLException {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            String[] string1 = {"1", "2", "3"};
            String[] string2 = {"4", "5", "6"};
            String[] string3 = {"7", "8", "9"};
            Item item1 = new Item("1", "1", "1", 1, string1);
            Item item2 = new Item("2", "2", "2", 2, string2);
            Item item3 = new Item("3", "2", "3", 3, string3);
            trackerSQL.add(item1);
            trackerSQL.add(item2);
            trackerSQL.add(item3);
            ArrayList<Item> expected = new ArrayList<>(Arrays.asList(item2, item3));
            assertThat(trackerSQL.findByName("2"), is(expected));
        }
    }

}