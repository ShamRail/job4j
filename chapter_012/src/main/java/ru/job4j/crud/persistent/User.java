package ru.job4j.crud.persistent;

import java.util.Date;
import java.util.Objects;

public class User {

    private final int id;

    private final String login;

    private final String password;

    private final String email;

    private final String createDate;

    public User(int id, String login, String password, String email, String createDate) {
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

    public String getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return String.format("id: %-4d login: %-10s password: %-10s email: %-15s create_date: %s",
                id, login, password, email, new Date(createDate));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && createDate.equals(user.createDate) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, createDate);
    }
}
