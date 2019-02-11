package ru.job4j.isp;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Describe simple menu with tree structure.
 */
public class Menu {

    /**
     * high level task's without parent.
     */
    private List<MenuItem> menu = new LinkedList<>();

    /**
     * show task's info.
     */
    public void showMenu() {
        menu.forEach(MenuItem::show);
    }

    /**
     * Add new item to parent item, if it exist's.
     * @param parent parent's name.
     * @param itemName new item's name.
     * @return true if new item added, else false.
     */
    public boolean addMenuItem(String parent, String itemName) {
        boolean result = false;
        MenuItem item = new MenuItem(itemName);
        if (parent == null) {
            item.setLevel(0);
            item.setPrefix("");
            item.setNumber(menu.size() + 1);
            menu.add(item);
            result = true;
        } else {
            Queue<MenuItem> queue = new LinkedList<>(menu);
            while (!queue.isEmpty()) {
                MenuItem parentItem = queue.poll();
                if (parentItem.getName().equals(parent)) {
                    item.setLevel(parentItem.getLevel() + 1);
                    item.setPrefix(String.format("%s%s.", parentItem.getPrefix(), parentItem.getNumber()));
                    item.setNumber(parentItem.getSubItemsCount() + 1);
                    parentItem.getSubItems().add(item);
                    result = true;
                    break;
                }
                parentItem.getSubItems().forEach(queue::offer);
            }
        }
        return result;
    }

}
