package jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StoreSQL {

    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());
    /**DB configuration.*/
    private Config config = new Config();
    /**DB connection.*/
    private Connection connection = null;

    public StoreSQL() {
        initConnection();
        initDB();
    }

    /**
     * initConnection.
     * Initialize connection to DB.
     * */
    private void initConnection() {
        try {
            connection = DriverManager.getConnection(config.get("url"));
            LOG.debug("Connection has initialized successfully.");
        } catch (Exception e) {
            LOG.error(String.format("Connection has fallen! %s occurs with %s", e.getClass().getSimpleName(), e.getMessage()));
        }
    }
    /**
     * initDB.
     * Create tables, if it doesn't exist.
     * */
    private void initDB() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("drop table if exists  entry;");
            statement.execute("create table entry(field integer);");
            LOG.debug("Entry table has created again.");
        } catch (Exception e) {
            LOG.trace(String.format("Table's creation has fallen with %s!", e.getClass().getSimpleName()));
        }
    }
    /**
     * generate.
     * Insert into table first n numbers.
     * @param n - last number.
     * */
    public void generate(int n) {
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("insert into entry(field) values(?);")) {
                for (int i = 1; i <= n; i++) {
                    statement.setInt(1, i);
                    statement.addBatch();
                }
                statement.executeBatch();
                connection.commit();
                connection.setAutoCommit(true);
                LOG.debug("All numbers have inserted!");
            }
        } catch (Exception e) {
            LOG.error(String.format("Insertion has fallen with %s. Exception args %s",
                    e.getClass().getSimpleName(), e.getMessage()));
        }
    }
    /**
     * getEntriesList.
     * @return entry table as list.
     * */
    public List<Entry> getEntriesList() {
        List<Entry> result = new LinkedList<>();
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from entry;")) {
            while (resultSet.next()) {
                result.add(new Entry(resultSet.getInt(1)));
            }
            LOG.debug("List of entries has loaded.");
        } catch (Exception e) {
            LOG.error(String.format("Loading has fallen with %s", e.getClass().getSimpleName()));
        }
        return result;
    }

}
