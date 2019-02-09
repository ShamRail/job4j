package ru.job4j.lsp;

import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.store.Store;

/**
 * Strategy executor. Context.
 */
public class FoodInput {

    /**
     * Concrete strategy.
     */
    private Store store;

    public FoodInput(Store store) {
        this.store = store;
    }

    public void setStoreType(Store store) {
        this.store = store;
    }

    public void distribute(Food food) {
        this.store.storeFood(food);
    }
}
