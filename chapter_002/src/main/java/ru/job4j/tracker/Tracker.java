package ru.job4j.tracker;

import java.util.*;
import java.lang.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Tracker.
 * Класс - хранилище ячеек. Имеет возможность.
 * добавлять, удалять, искать ячейки.
 * @version 1.0.
 * @since 10.07.2018.
 * @author Rail Shamsemukhametov.
 * */

public class Tracker {
    /**
     * items - массив ячеек.
     * */
    private ArrayList<Item> items = new ArrayList<Item>();
    /**
     * RN - объект генерирующий id.
     * */
    private static final Random RN = new Random();
    /**
     * add.
     * Метод, добавляющий ячейку в массив, прежде генерируя id.
     * @param item - ячейка.
     * @return - ячейка.
     * */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }
    /**
     * idComparing.
     * Сравнивает идентификаторы.
     * @param id идентификатор.
     * @param item ячейка.
     * @return результат.
     * */
    private boolean idComparing(Item item, String id) {
        Predicate<String> predicate = (itemId) -> item.getId().equals(itemId);
        return predicate.test(id);
    }

    /**
     * replace.
     * Метод, меняюший значение ячеек по id.
     * @param id - идентификатор , заменяемой ячейки.
     * @param item - ячейка, на которую меняем.
     * */
    public void replace(String id, Item item) {
        for (Item item1 : this.items) {
            if (idComparing(item1, id)) {
                item.setId(id);
                this.items.set(this.items.indexOf(item1), item);
                break;
            }
        }
    }
    /**
     * delete.
     * Метод, удаляющий ячейку по идентификатору.
     * @param id - идентификатор.
     * */
    public boolean delete(String id) {
        boolean del = false;
        for (Item item : this.items) {
            if (id != null && idComparing(item, id)) {
                this.items.remove(item);
                del = true;
                break;
            }
        }
        return del;
    }
    /**
     * findAll.
     * Метод, возвращающий массив непустых ячеек.
     * @return - массив ячеек.
     * */
    public ArrayList<Item> findAll() {
        return this.items;
    }
    /**
     * findByName.
     * Метод, ищущий ячейки по имени.
     * @param key - имя.
     * @return массив ячеек с таким именем.
     * */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<Item>();
        if (this.items.size() != 0 && key != null) {
            for (Item item : this.items) {
                if (item.getName().equals(key)) {
                    result.add(item);
                }
            }
        }
        return result;
    }
    /**
     * findById.
     * Метод, ищущий ячейку по идентификатору.
     * Если ее нет, то null.
     * @param id - идентификатор.
     * @return - ячейка.
     * */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && idComparing(item, id)) {
                result = item;
                break;
            }
        }
        return result;
    }
    /**
     * addCommentById.
     * Метод, добавляющий комментарий по идентификатору.
     * @param id - идентификатор.
     * @param comment - комментарий
     * */
    public boolean addCommentById(String id, String comment) {
        boolean res = false;
        if (id != null && comment != null) {
            for (Item item : this.items) {
                if (idComparing(item, id)) {
                    item.setComments(comment);
                    res = true;
                    break;
                }
            }
        }
        return res;
    }
    /**
     * generateId.
     * Метод, генерирующий идентификатор.
     * @return - сгенерированный идентификатор.
     * */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
}
