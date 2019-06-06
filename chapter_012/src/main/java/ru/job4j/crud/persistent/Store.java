package ru.job4j.crud.persistent;

import java.util.concurrent.CopyOnWriteArrayList;

public interface Store {

    void add(User user);

    void update(int id, User newUser);

    void delete(int id);

    User findById(int id);

    CopyOnWriteArrayList<User> findAll();

}
