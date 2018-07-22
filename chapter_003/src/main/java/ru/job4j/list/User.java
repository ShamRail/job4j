package ru.job4j.list;

public class User {
    private String name;
    private String city;
    private Integer id;

    public User(Integer id, String name, String city) {
        this.name = name;
        this.city = city;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public Integer getId() {
        return this.id;
    }
}
