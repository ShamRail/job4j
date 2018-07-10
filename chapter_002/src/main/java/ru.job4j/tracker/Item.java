package ru.job4j.tracker;

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
    /**Комментарий*/
    private String comment;

    public Item() {

    }

    public Item(String id, String name,
                String description, long create, String comment) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.create = create;
        this.comment = comment;
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

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getComment() {
        return this.comment;
    }

    public long getCreate() {
        return this.create;
    }
}
