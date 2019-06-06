package ru.job4j.crud.persistent;

import java.util.concurrent.CopyOnWriteArrayList;

public class MemoryStore implements Store {

    private CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();

    private static final MemoryStore INSTANCE = new MemoryStore();

    private MemoryStore() {

    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void update(int id, User newUser) {
        users.remove(findById(id));
        users.add(newUser);
    }

    @Override
    public void delete(int id) {
        users.remove(findById(id));
    }

    @Override
    public User findById(int id) {
        User result = null;
        for (User user : users) {
            if (user.getId() == id) {
                result = user;
                break;
            }
        }
        return result;
    }

    @Override
    public CopyOnWriteArrayList<User> findAll() {
        return users;
    }

}
