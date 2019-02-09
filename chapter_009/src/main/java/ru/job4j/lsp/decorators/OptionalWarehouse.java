package ru.job4j.lsp.decorators;

import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.store.Warehouse;

import java.util.LinkedList;
import java.util.List;

/**
 * Extend warehouse by new store.
 */
public class OptionalWarehouse extends Warehouse {

    /**
     * input warehouse.
     */
    private Warehouse input;

    /**
     * bound on food count in input.
     */
    private final int bound;

    /**
     * new store.
     */
    private List<Food> newStore = new LinkedList<>();

    public OptionalWarehouse(Warehouse warehouse, int bound) {
        this.input = warehouse;
        this.bound = bound;
    }

    /**
     * put food to new store if input store is not free.
     * @param food food.
     * @return true.
     */
    @Override
    public boolean storeFood(Food food) {
        if (input.getFoodsCount() < bound) {
            input.storeFood(food);
        } else {
            newStore.add(food);
        }
        return true;
    }
}
