package ru.job4j;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "7";
    /**
     * Константа меню для показа списка заявок.
     */
    private static final String SHOW = "1";
    /**
     * Константа меню для редактирования новой заявки.
     */
    private static final String EDIT = "2";
    /**
     * Константа меню для удаления заявки.
     */
    private static final String DELETE = "3";
    /**
     * Константа меню для поиска заявки по id.
     */
    private static final String FINDBYID = "4";
    /**
     * Константа меню для поиска заявки по по имени.
     */
    private static final String FINDBYNAME = "5";
    /**
     * Константа меню для добавления комментария по id.
     */
    private static final String ADDCOMMNET = "6";

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        boolean exit = false;
        while (!exit) {
            System.out.printf("%s%s%s%s", "Меню.", System.lineSeparator(),
                    "Выберите пункт меню :", System.lineSeparator());
            menu.show();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                menu.select(Integer.valueOf(answer));
            } else if (SHOW.equals(answer)) {
                menu.select(Integer.valueOf(answer));
            } else if (EDIT.equals(answer)) {
                menu.select(Integer.valueOf(answer));
            } else if (DELETE.equals(answer)) {
                menu.select(Integer.valueOf(answer));
            } else if (FINDBYID.equals(answer)) {
                menu.select(Integer.valueOf(answer));
            } else if (FINDBYNAME.equals(answer)) {
                menu.select(Integer.valueOf(answer));
            } else if (ADDCOMMNET.equals(answer)) {
                menu.select(Integer.valueOf(answer));
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

   /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();

    }
}