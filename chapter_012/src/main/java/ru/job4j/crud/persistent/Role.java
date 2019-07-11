package ru.job4j.crud.persistent;

public class Role {

    public final static String ADMIN = "admin";

    public final static String USER = "user";

    private final String role;

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
