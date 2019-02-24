package ru.job4j.isp;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents menu item.
 */

public class MenuItem {

    /**
     * item's name.
     */
    private String name;

    /**
     * order number.
     */
    private int number;

    /**
     * up level task prefix including it's order number.
     */
    private String prefix;

    /**
     * depth level.
     */
    private int level;

    /**
     * task's children.
     */
    private List<MenuItem> subItems = new LinkedList<>();

    public MenuItem(String name) {
        this.name = name;
    }

    /**
     * Adds subItem.
     * @param item subItem.
     */
    public void addSubItem(MenuItem item) {
        this.subItems.add(item);
    }

    /**
     * @return tash's child count.
     */
    public int getSubItemsCount() {
        return this.subItems.size();
    }

    /**
     * write task name and prefix with order number.
     */
    public void show() {
        for (int i = 0; i < level * 2; i++) {
            System.out.print("-");
        }
        System.out.print(String.format("%s%s ", (getLevel() == 0) ? "" : " ", name));
        if (!prefix.equals("")) {
            System.out.print(prefix);
        }
        System.out.println(number);
        subItems.forEach(MenuItem::show);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<MenuItem> getSubItems() {
        return subItems;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
