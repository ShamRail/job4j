package ru.job4j.cinema.models;

import java.util.Objects;

public class Account {

    private int id;

    private final String name;

    private final String telNumber;

    private final Place place;

    public Account(String name, String telNumber, Place place) {
        this.name = name;
        this.telNumber = telNumber;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public Place getPlace() {
        return place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                Objects.equals(name, account.name) &&
                Objects.equals(telNumber, account.telNumber) &&
                Objects.equals(place, account.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, telNumber, place);
    }
}
