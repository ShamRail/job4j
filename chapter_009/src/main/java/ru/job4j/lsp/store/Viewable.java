package ru.job4j.lsp.store;

import ru.job4j.lsp.food.Food;

import java.util.Collection;

public interface Viewable {

    Collection<Food> view();

}
