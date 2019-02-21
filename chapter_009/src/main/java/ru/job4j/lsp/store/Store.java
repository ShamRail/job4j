package ru.job4j.lsp.store;

import ru.job4j.lsp.food.Food;

/**
 * Describe store's strategy.
 */

public interface Store extends Viewable, Cleanable {

    /**
     * put food to store.
     * @param food food.
     * @return true if it has done else false.
     */
    boolean storeFood(Food food);

    /**
     * add food to food list.
     * @param food food.
     */
    void putFood(Food food);
}
