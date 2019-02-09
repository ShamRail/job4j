package ru.job4j.lsp.store;

import ru.job4j.lsp.food.Food;

import java.util.LinkedList;
import java.util.List;

/**
 * Describe store's strategy.
 */

public abstract class Store {

    /**
     * food's in store.
     */
    protected List<Food> foods = new LinkedList<>();

    /**
     * put food to store.
     * @param food food.
     * @return true if it has done else false.
     */
    public abstract boolean storeFood(Food food);

    /**
     * add food to food list.
     * @param food food.
     */
    protected void putFood(Food food) {
        this.foods.add(food);
    }

    /**
     * @return foods count.
     */
    public int getFoodsCount() {
        return this.foods.size();
    }

    public List<Food> getFoods() {
        return this.foods;
    }
}
