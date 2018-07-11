package ru.job4j.tracker;

import java.util.*;
import java.lang.*;

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
    private Item[] items = new Item[100];
    /**
     * position - конечная позиция не нулевого элемента.
     * */
    private int position = 0;
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
        this.items[this.position++] = item;
        return item;
    }
    /**
     * replace.
     * Метод, меняюший значение ячеек по id.
     * @param id - идентификатор , заменяемой ячейки.
     * @param item - ячейка, на которую меняем.
     * */
    public void replace(String id, Item item) {
        for (int i = 0; i != this.position; i++) {
            if (items[i].getId().equals(id)) {
                items[i] = item;
                items[i].setId(id);
                break;
            }
        }
    }
    /**
     * delete.
     * Метод, удаляющий ячейку по идентификатору.
     * @param id - идентификатор.
     * */
    public void delete(String id) {
        if (this.position != 0) {
            int delBegin = -1;
            for (int i = 0; i != this.position; i++) {
                if (this.items[i].getId().equals(id)) {
                    delBegin = i;
                    break;
                } else {
                    delBegin++;
                }
            }
            if (delBegin != -1) {
                System.arraycopy(items, delBegin + 1, items, delBegin,
                        items.length - delBegin - 1);
                this.items[items.length - 1] = null;
                position--;
            }
        }

    }
    /**
     * findAll.
     * Метод, возвращающий массив непустых ячеек.
     * @return - массив ячеек.
     * */
    public Item[] findAll() {
        return Arrays.copyOf(this.items, position);
    }
    /**
     * findByName.
     * Метод, ищущий ячейки по имени.
     * @param key - имя.
     * @return массив ячеек с таким именем.
     * */
    public Item[] findByName(String key) {
        Item[] result = new Item[this.position];
        if (this.position != 0) {
            int index = 0;
            for (int i = 0; i != this.position; i++) {
                if (items[i].getName().equals(key)) {
                    result[index++] = items[i];
                }
            }
            if (index != 0) {
                Arrays.copyOf(result, index);
            } else {
                result = null;
            }

        } else {
            result = null;
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
        for (Item item1 : items) {
            if (item1 != null && item1.getId().equals(id)) {
                result = item1;
                break;
            }
        }
        return result;
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
