package ru.job4j.tracker;

import java.util.ArrayList;

public interface ITracker {
    Item add(Item item);
    void replace(String id, Item item);
    boolean delete(String id);
    ArrayList<Item> findAll();
    ArrayList<Item> findByName(String key);
    Item findById(String id);
    boolean addCommentById(String id, String comment);
}
