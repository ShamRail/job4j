package ru.job4j.bank;

/**
 * User.
 * Класс пользователь.
 * @version 1.0.
 * @author Rail Shamsemukhametov.
 * @since 24.07.2018.
 * */

public class User {
    /**Имя пользователя*/
    private String name;
    /**Паспорт пользователя*/
    private String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return this.name;
    }

    public String getPassport() {
        return this.passport;
    }
}
