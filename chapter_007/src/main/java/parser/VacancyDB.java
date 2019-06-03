package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class VacancyDB implements AutoCloseable {

    private Properties config;

    private Connection connection = null;

    private static final Logger LOG = LogManager.getLogger(VacancyDB.class.getName());

    public VacancyDB(Properties properties) {
        config = properties;
        createIfNotExists();
        connect();
    }

    private void createIfNotExists() {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            LOG.debug("Loading DB driver ...");
            Class.forName(config.getProperty("driver-class-name"));
            LOG.debug("Creating connection ...");
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            statement = connection.createStatement();
            LOG.debug("Check DB existing ...");
            resultSet = statement.executeQuery("select true as exists from pg_database where datname = 'parser';");
            if (!(resultSet.next() && resultSet.getString("exists").equals("t"))) {
                statement.execute("create database parser");
                LOG.debug("DB is created.");
            }
            LOG.debug("DB is initiated.");
        } catch (Exception e) {
            LOG.fatal("Failure to DB creating.");
            e.printStackTrace();
        } finally {
            closeObj(connection);
            closeObj(statement);
            closeObj(resultSet);
        }
    }

    private void connect() {
        LOG.debug("Connecting to DB ...");
        Statement statement = null;
        try {
            this.connection = DriverManager.getConnection(
                    config.getProperty("parser_url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            statement = connection.createStatement();
            LOG.debug("Creating table for vacancies ...");
            statement.execute(String.format("create table if not exists job(%s, %s, %s, %s, %s);",
                    "id serial primary key",
                    "name varchar(255)",
                    "description text",
                    "link varchar(255)",
                    "create_date bigint"));
            LOG.debug("DB has initiated.");
        } catch (Exception e) {
            LOG.fatal("Can not connect to DB.");
            e.printStackTrace();
        } finally {
            closeObj(statement);
        }
    }

    /***
     * Insert vacancies into DB.
     * @param vacancies vacancies.
     */

    public void addData(List<Vacancy> vacancies) {
        try {
            LOG.debug("Starting inserting to DB ...");
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement("insert into job(name, description, link, create_date) values(?, ?, ?, ?);")) {
                for (Vacancy vacancy : vacancies) {
                    statement.setString(1, vacancy.getName());
                    statement.setString(2, vacancy.getText());
                    statement.setString(3, vacancy.getLink());
                    statement.setLong(4, vacancy.getVacancyDate().getTimeDate());
                    statement.addBatch();
                }
                statement.executeBatch();
                connection.commit();
                connection.setAutoCommit(true);
            }
            LOG.debug("Inserting has successfully finished.");
        } catch (SQLException e) {
            LOG.error(String.format("Failure to inserting data. %s", e.getMessage()));
        }
    }

    /***
     * Get time of last updated vacancy.
     * @return time in milliseconds.
     */

    public long lastJobTime() {
        LOG.debug("Getting time of last updated vacancies ...");
        long result = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select max(create_date) from job;");
            resultSet.next();
            result = resultSet.getLong(1);
            LOG.debug("Time is gotten.");
        } catch (SQLException e) {
            LOG.error(String.format("Getting was fallen. %s", e.getMessage()));
        }
        return result;
    }

    private void closeObj(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception sql) {
                sql.printStackTrace();
            }
        }
    }

    @Override
    public void close() {
        closeObj(connection);
    }

}
