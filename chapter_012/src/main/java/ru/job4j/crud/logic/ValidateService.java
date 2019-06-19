package ru.job4j.crud.logic;

import ru.job4j.crud.persistent.DBStore;
import ru.job4j.crud.persistent.Store;
import ru.job4j.crud.persistent.User;
import java.util.concurrent.CopyOnWriteArrayList;

public class ValidateService {

    private static final ValidateService INSTANCE = new ValidateService();

    private Store memoryStore = DBStore.getInstance();

    private ValidateService() {

    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public void add(User user) throws ValidationException {
        if (memoryStore.findById(user.getId()) != null) {
            throw new ValidationException("User is already existed!");
        }
        memoryStore.add(user);
    }

    public void update(int id, User newUser) throws ValidationException {
        if (memoryStore.findById(id) == null) {
            throw new ValidationException("User is not existed!");
        }
        memoryStore.update(id, newUser);
    }

    public void delete(int id) throws ValidationException {
        if (memoryStore.findById(id) == null) {
            throw new ValidationException("User is not existed!");
        }
        memoryStore.delete(id);
    }

    public User findById(int id) throws ValidationException {
        User user = memoryStore.findById(id);
        if (user == null) {
            throw new ValidationException("User is not existed!");
        }
        return user;
    }

    public CopyOnWriteArrayList<User> findAll() {
        return memoryStore.findAll();
    }

    public boolean contains(int id) {
        return memoryStore.findById(id) != null;
    }

    public int getMaxID() {
        return memoryStore.findAll().stream().mapToInt(User::getId).max().orElse(0);
    }

}
