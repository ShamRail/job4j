package ru.job4j.crud.presentation;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.persistent.Store;
import ru.job4j.crud.persistent.User;
import java.util.concurrent.CopyOnWriteArrayList;

public class ValidateStub implements Validate {

    private CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void update(int id, User newUser) {
        delete(id);
        users.add(newUser);
    }

    @Override
    public void delete(int id) {
        users.remove(findById(id));
    }

    @Override
    public User findById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    @Override
    public CopyOnWriteArrayList<User> findAll() {
        return users;
    }

    @Override
    public int getMaxID() {
        return users.stream().mapToInt(User::getId).max().orElse(0);
    }

    @Override
    public User findByLogin(String login) {
        return users.stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
    }
}
