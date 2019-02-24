package ru.job4j.lsp.decorators;

import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.store.Trash;

import java.util.LinkedList;
import java.util.List;

/**
 * Extend trash store by reproducing foods.
 */

public class ReproducingTrash extends Trash {

    /**
     * input trash.
     */
    private Trash trash;

    public ReproducingTrash(Trash trash) {
        this.trash = trash;
    }

    @Override
    public boolean storeFood(Food food) {
        this.trash.storeFood(food);
        this.reproduceFood();
        return true;
    }

    /**
     * Choose random food's and reproduced it by removing from trash.
     */
    private void reproduceFood() {
        List<Food> foods = (List<Food>) trash.view();
        List<Food> reproduced = new LinkedList<>();
        boolean isReproduced;
        for (Food food : foods) {
            isReproduced = Math.random() > 0.5;
            if (isReproduced) {
                reproduced.add(food);
            }
        }
        foods.removeAll(reproduced);
    }
}
