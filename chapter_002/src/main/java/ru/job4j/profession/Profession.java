package ru.job4j.profession;

public class Profession {
    private String name;
    private Profession profession;

    public Profession() {

    }

    public Profession(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
