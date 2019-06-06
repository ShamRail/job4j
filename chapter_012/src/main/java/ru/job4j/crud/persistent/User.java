package ru.job4j.crud.persistent;

import java.util.Date;

public class User {

    private final int id;

    private final String login;

    private final String password;

    private final String email;

    private final long createDate;

    public User(int id, String login, String password, String email, long createDate) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public long getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return String.format("id: %-4d login: %-10s password: %-10s email: %-15s create_date: %s",
                id, login, password, email, new Date(createDate));
    }

}
