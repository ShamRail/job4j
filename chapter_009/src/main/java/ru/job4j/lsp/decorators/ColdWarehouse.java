package ru.job4j.lsp.decorators;


import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.food.Vegetable;
import ru.job4j.lsp.store.Warehouse;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Extend warehouse by using specific warehouse for vegetables.
 */
public class ColdWarehouse extends Warehouse {

    /**
     * income warehouse.
     */
    private Warehouse warehouse;

    /**
     * specific store for vegetables.
     */
    private List<Food> coldWareHouse = new LinkedList<>();

    public ColdWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public boolean storeFood(Food food) {
        this.warehouse.storeFood(food);
        this.replaceVegetables();
        return true;
    }

    /**
     * replace vegetables to cold warehouse.
     */
    private void replaceVegetables() {
        List<Food> foods = warehouse.getFoods();
        List<Food> vegetables = foods.stream().filter(el -> el instanceof Vegetable).collect(Collectors.toList());
        coldWareHouse.addAll(vegetables);
        foods.removeAll(vegetables);
    }
}
