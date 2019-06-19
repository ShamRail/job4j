package ru.job4j.crud.persistent;

import org.apache.commons.dbcp2.BasicDataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class DBStore implements Store {

    private static final BasicDataSource SOURCE = new BasicDataSource();

    private static final DBStore INSTANCE = new DBStore();

    private DBStore() {
        initConfig();
        initTable();
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

    private void initTable() {
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()) {
           statement.execute(String.format("create table if not exists users(%s, %s, %s, %s, %s);",
                   "id int",
                   "login varchar(255)",
                   "password varchar(255)",
                   "email varchar(255)",
                   "create_date varchar(255)")
           );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(
                             "insert into users(id, login, password, email, create_date) values(?, ?, ?, ?, ?);")
        ) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getCreateDate());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, User newUser) {
        try (   Connection connection = SOURCE.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "update users set login = ?, password = ?, email = ?, create_date = ? where id = ?;")
        ) {
            preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setString(3, newUser.getEmail());
            preparedStatement.setString(4, newUser.getCreateDate());
            preparedStatement.setInt(5, id);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(String.format("delete from users where id = %s;", id))
        ) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet set = statement.executeQuery(String.format("select * from users where id = %s;", id))
        ) {
            String login;
            String password;
            String email;
            String create_date;
            while (set.next()) {
                login = set.getString(2);
                password = set.getString(3);
                email = set.getString(4);
                create_date = set.getString(5);
                user = new User(id, login, password, email, create_date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public CopyOnWriteArrayList<User> findAll() {
        CopyOnWriteArrayList<User> result = new CopyOnWriteArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet set = statement.executeQuery("select * from users;")
        ) {
            int id;
            String login;
            String password;
            String email;
            String create_date;
            while (set.next()) {
                id = set.getInt(1);
                login = set.getString(2);
                password = set.getString(3);
                email = set.getString(4);
                create_date = set.getString(5);
                result.add(new User(id, login, password, email, create_date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
