package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Item.
 * Класс - ячейка .
 * @version 1.0.
 * @since 10.07.2018.
 * @author Rail Shamsemukhametov.
 * */
public class Item {
    /**Идентификатор*/
    private String id;
    /**Имя*/
    private String name;
    /**Описание*/
    private String description;
    /**Время создания миллисекундах*/
    private long create;
    /**Комментарии*/
    private ArrayList<String> comments = new ArrayList<>();
    /**Позиция комментария*/
    public Item() {

    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Item(String id, String name,
                String description, long create, String[] comments) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.create = create;
        this.comments.addAll(Arrays.asList(comments));
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComments(String comment) {
        this.comments.add(comment);
    }

    public void setCreate(long create) {
        this.create = create;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<String> getComments() {
        return this.comments;
    }

    public long getCreate() {
        return this.create;
    }


    @Override
    public String toString() {
        return String.format("идентификатор : %s, имя : %s, описание заявки : %s", id, name, description);
    }
}
