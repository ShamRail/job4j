package ru.job4j.lsp.food;

import java.util.Date;

/**
 * Vegetable.
 */

public class Vegetable extends Food {
    public Vegetable(String name, Date createDate, Date expiredDate, float price) {
        super(name, createDate, expiredDate, price);
    }
}
