package ru.job4j.crud.persistent;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TownsDAO {

    private static final BasicDataSource SOURCE = new BasicDataSource();

    private static final TownsDAO DAO = new TownsDAO();

    private TownsDAO() {
        initConfig();
        initTables();
        fillTables();
    }

    private void initConfig() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/users");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    private void initTables() {
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()) {
             statement.execute("drop table if exists cities");
             statement.execute("drop table if exists countries;");
             statement.execute(String.format("create table countries(%s, %s);",
                    "id serial primary key",
                    "name varchar(255)")
             );
             statement.execute(String.format("create table cities(%s, %s, %s);",
                    "id serial primary key",
                    "name varchar(255)",
                     "country_id int references countries(id)")
             );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillTables() {
        try (Connection connection = SOURCE.getConnection();
             Statement stmt = connection.createStatement()) {
             connection.setAutoCommit(false);
             stmt.addBatch("insert into countries(name) values('Russia');");
             stmt.addBatch("insert into countries(name) values('USA');");
             stmt.addBatch("insert into countries(name) values('UK');");
             stmt.executeBatch();
             connection.commit();

             stmt.addBatch("insert into cities(name, country_id) values('Moscow', 1);");
             stmt.addBatch("insert into cities(name, country_id) values('St. Petersburg', 1);");
             stmt.addBatch("insert into cities(name, country_id) values('Ufa', 1);");

             stmt.addBatch("insert into cities(name, country_id) values('New York', 2);");
             stmt.addBatch("insert into cities(name, country_id) values('Los-Vegas', 2);");
             stmt.addBatch("insert into cities(name, country_id) values('Washington', 2);");

             stmt.addBatch("insert into cities(name, country_id) values('London', 3);");
             stmt.addBatch("insert into cities(name, country_id) values('Glasgow', 3);");
             stmt.addBatch("insert into cities(name, country_id) values('Edinburgh', 3);");

             stmt.executeBatch();
             connection.commit();
             connection.setAutoCommit(true);
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }

    public static TownsDAO getInstance() {
        return DAO;
    }

    public List<String> getCountries() {
        List<String> countries = new CopyOnWriteArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("select name from countries;")) {
             while (rs.next()) {
                 String cntr = rs.getString(1);
                 countries.add(cntr);
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    public List<String> getTowns(String country) {
        List<String> towns = new CopyOnWriteArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(String.format("%s = (%s '%s');",
                     "select t.name from cities as t where t.country_id",
                     "select id from countries where name like ",
                     country));
        ) {
            while (rs.next()) {
                String twn = rs.getString(1);
                towns.add(twn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return towns;
    }

}
