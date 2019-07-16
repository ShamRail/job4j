package ru.job4j.crud.persistent;

import java.util.Date;
import java.util.Objects;

public class User {

    private final int id;

    private final String login;

    private final String password;

    private final String email;

    private final String createDate;

    private final Role role;

    private final String country;

    private final String town;

    public User(int id, String login, String password, String email, String createDate, Role role, String country, String town) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.role = role;
        this.country = country;
        this.town = town;
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

    public Role getRole() {
        return role;
    }

    public String getCountry() {
        return country;
    }

    public String getTown() {
        return town;
    }

    @Override
    public String toString() {
        return String.format("id: %-4d login: %-10s password: %-10s email: %-15s create_date: %s",
                id, login, password, email, createDate);
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
