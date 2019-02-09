package ru.job4j.lsp.store;

import ru.job4j.lsp.food.Food;


/**
 * Describe trash store.
 */
public class Trash extends Store {

    @Override
    public boolean storeFood(Food food) {
        super.putFood(food);
        return true;
    }
}
