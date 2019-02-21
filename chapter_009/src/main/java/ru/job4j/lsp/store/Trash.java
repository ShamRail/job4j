package ru.job4j.lsp.store;

import ru.job4j.lsp.food.Food;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


/**
 * Describe trash store.
 */
public class Trash implements Store {

    /**
     * food's in store.
     */
    protected List<Food> foods = new LinkedList<>();
    @Override
    public boolean storeFood(Food food) {
        putFood(food);
        return true;
    }

    @Override
    public void putFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public void clean() {
        this.foods.clear();
    }

    @Override
    public Collection<Food> view() {
        return this.foods;
    }
}
