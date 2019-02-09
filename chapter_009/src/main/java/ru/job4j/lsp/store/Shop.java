package ru.job4j.lsp.store;

import ru.job4j.lsp.food.Food;

/**
 * Describe shop store.
 */

public class Shop extends Store {

    /**
     * Put food to food list.
     * @param food income food.
     * @return true.
     */
    @Override
    public boolean storeFood(Food food) {
        super.putFood(food);
        return true;
    }

}
