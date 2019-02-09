package ru.job4j.lsp;

import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.store.Shop;
import ru.job4j.lsp.store.Store;
import ru.job4j.lsp.store.Trash;
import ru.job4j.lsp.store.Warehouse;

import java.util.Date;

/**
 * Food's distributor.
 */
public class ControlQuality {

    /**
     * Warehouse.
     */
    private Store warehouse = new Warehouse();

    /**
     * Shop.
     */
    private Store shop = new Shop();

    /**
     * Trash.
     */
    private Store trash = new Trash();

    /**
     * Distributor.
     */
    private FoodInput distributor = new FoodInput(warehouse);

    /**
     * Send food to strategy executor.
     * @param food
     */
    public void sendStore(Food food) {
        distributor.setStoreType(chooseStore(food, new Date(System.currentTimeMillis())));
        distributor.distribute(food);
    }

    /**
     * Define needed store for food by date.
     * @param food food.
     * @param time date.
     * @return defined store.
     */
    private Store chooseStore(Food food, Date time) {
        short percent = food.getUsingPercentage(time);
        Store result = warehouse;
        if (percent >= 25 && percent < 75) {
            result = shop;
        }
        if (percent >= 75 && percent < 100) {
            food.reducePrice(100 - percent);
            result = shop;
        }
        if (percent >= 100) {
            result = trash;
        }
        return  result;
    }

}
