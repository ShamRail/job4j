package ru.job4j.cinema.models;

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
}
