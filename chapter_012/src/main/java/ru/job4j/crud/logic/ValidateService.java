package ru.job4j.crud.logic;

import ru.job4j.crud.persistent.MemoryStore;
import ru.job4j.crud.persistent.User;
import java.util.concurrent.CopyOnWriteArrayList;

public class ValidateService {

    private static final ValidateService INSTANCE = new ValidateService();

    private MemoryStore memoryStore = MemoryStore.getInstance();

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

    public CopyOnWriteArrayList<User> findAll() throws ValidationException {
        CopyOnWriteArrayList<User> users = memoryStore.findAll();
        if (users.isEmpty()) {
            throw new ValidationException("No one user was created!");
        }
        return users;
    }

    public boolean contains(int id) {
        return memoryStore.findById(id) != null;
    }

}
