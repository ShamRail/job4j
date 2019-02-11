package ru.job4j.isp;

/**
 * Start application.
 */
public class MenuUI {

    private Menu menu = new Menu();

    public MenuUI() {
        menu.addMenuItem(null, "first");
        menu.addMenuItem(null, "second");
        menu.addMenuItem(null, "third");
        menu.addMenuItem("first", "first first");
        menu.addMenuItem("first first", "first first first");
        menu.addMenuItem("first first", "first first second");
        menu.addMenuItem("first first", "first first third");
        menu.addMenuItem("first first", "first first fourth");
    }

    public void start() {
        menu.showMenu();
    }

}
