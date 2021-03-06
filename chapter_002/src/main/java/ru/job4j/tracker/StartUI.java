package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private  final Input input;

    /**
     * Хранилище заявок.
     */
    private final ITracker tracker;

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
    private boolean exit = false;

    public void stop() {
        this.exit = true;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);

        while (!exit) {
            System.out.printf("%s%s%s%s", "Меню.", System.lineSeparator(),
                    "Выберите пункт меню :", System.lineSeparator());
            menu.show();
            int answer = this.input.ask("Выберите пункт меню :", menu.fillMenuRange());
            menu.select(answer);
        }
    }

   /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}