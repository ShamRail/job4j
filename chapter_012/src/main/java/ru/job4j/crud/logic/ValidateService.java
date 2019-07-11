package ru.job4j.crud.logic;

import ru.job4j.crud.persistent.DBStore;
import ru.job4j.crud.persistent.Store;
import ru.job4j.crud.persistent.User;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ValidateService implements Validate {

    private static final ValidateService INSTANCE = new ValidateService();

    private Store memoryStore = DBStore.getInstance();

    private ValidateService() {

    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) throws ValidationException {
        if (memoryStore.findById(user.getId()) != null) {
            throw new ValidationException("User is already existed!");
        }
        memoryStore.add(user);
    }

    @Override
    public void update(int id, User newUser) throws ValidationException {
        if (memoryStore.findById(id) == null) {
            throw new ValidationException("User is not existed!");
        }
        memoryStore.update(id, newUser);
    }

    @Override
    public void delete(int id) throws ValidationException {
        if (memoryStore.findById(id) == null) {
            throw new ValidationException("User is not existed!");
        }
        memoryStore.delete(id);
    }

    @Override
    public User findById(int id) throws ValidationException {
        User user = memoryStore.findById(id);
        if (user == null) {
            throw new ValidationException("User is not existed!");
        }
        return user;
    }

    @Override
    public CopyOnWriteArrayList<User> findAll() {
        return memoryStore.findAll();
    }

    public boolean contains(int id) {
        return memoryStore.findById(id) != null;
    }

    @Override
    public int getMaxID() {
        return memoryStore.findAll().stream().mapToInt(User::getId).max().orElse(0);
    }

    @Override
    public User findByLogin(String login) {
        List<User> users = memoryStore.findAll();
        User rst = null;
        for (User user : users) {
            if (login.equals(user.getLogin())) {
                rst = user;
                break;
            }
        }
        return rst;
    }

}
