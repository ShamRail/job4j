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
    /**Комментарии*/
    private String[] comments = new String[10];
    /**Позиция комментария*/
    private int commentposition = 0;
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
        for (int i = 0; i < comments.length; i++) {
            this.comments[this.commentposition++] = comments[i];
        }
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
        if (this.commentposition < 10) {
            this.comments[this.commentposition++] = comment;
        }
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

    public String[] getComments() {
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
