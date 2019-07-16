package ru.job4j.cinema.services;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.cinema.models.Account;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountsDAO {

    private static final BasicDataSource SOURCE = new BasicDataSource();

    private static final AccountsDAO INSTANCE = new AccountsDAO();

    private AccountsDAO() {
        initConfig();
        initTable();
    }

    private void initConfig() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/cinema");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    private void initTable() {
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()) {
             statement.execute("drop table if exists accounts;");
             statement.execute(String.format("create table if not exists accounts(%s, %s, %s);",
                    "id serial primary key",
                    "name varchar(255)",
                    "tel_number varchar(255)")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static AccountsDAO getInstance() {
        return INSTANCE;
    }

    public void create(Account account) {
        String name = account.getName();
        String telNumber = account.getTelNumber();
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()) {
             statement.execute(String.format("insert into accounts(name, tel_number) values('%s', '%s');", name, telNumber));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
