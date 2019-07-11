package ru.job4j.crud.logic;

import ru.job4j.crud.persistent.User;
import java.util.concurrent.CopyOnWriteArrayList;

public interface Validate {

    void add(User user) throws ValidationException;

    void update(int id, User newUser) throws ValidationException;

    void delete(int id) throws ValidationException;

    User findById(int id) throws ValidationException;

    CopyOnWriteArrayList<User> findAll();

    int getMaxID();

    User findByLogin(String login);

}
