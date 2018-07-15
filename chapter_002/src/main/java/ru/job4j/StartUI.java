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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FINDBYID.equals(answer)) {
                this.findItemById();
            } else if (FINDBYNAME.equals(answer)) {
                this.findItemsByName();
            } else if (ADDCOMMNET.equals(answer)) {
                this.addCommentsById();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }
    /**
     * Метод реализует  показ списка заявок.
     */
    private void showItems() {
        System.out.println("------------ Все заявки --------------");
        Item[] items = this.tracker.findAll();
        for (Item item : items) {
            System.out.println(item.toString(item.getId(), item.getName(), item.getDescription()));
        }
    }
    /**
     * Метод реализует редактирование заявки в хранилище.
     */
    private void editItem() {
        System.out.println("------------ Редактирование новой заявки --------------");
        String id = this.input.ask("Введите id редактируемой заявки : ");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        if (this.tracker.findById(id) != null) {
            this.tracker.replace(id, item);
            System.out.println("------------ Заявка успешно отредактирована --------------");
            System.out.println(item.toString(item.getName(), item.getDescription()));
        } else {
            System.out.println("Заяка не может быть отредактирована, неверный id");
        }

    }
    /**
     * Метод реализует удаление заявки из хранилища.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите id удаляемой заявки : ");
        boolean result = this.tracker.delete(id);
        if (!result) {
            System.out.println("Неверное id, повторите попытку");
        } else {
            System.out.println("------------ Заявка успешно удалена --------------");
        }
    }
    /**
     * Метод реализует поиск заявки по id.
     */
    private void findItemById() {
        System.out.println("------------ Поиск заявки по id --------------");
        String id = this.input.ask("Введите id искомой заявки : ");
        Item result = this.tracker.findById(id);
        if (result == null) {
            System.out.println("Заявка не найдена, повторите попытку");
        } else {
            System.out.println("------------ Заявка успешно найдена --------------");
            System.out.println(result.toString(result.getName(), result.getDescription()));
        }
    }
    /**
     * Метод реализует поиск заявок по имени.
     */
    private void findItemsByName() {
        System.out.println("------------ Поиск заявки по имени --------------");
        String name = this.input.ask("Введите имя искомых заявок : ");
        Item[] result = this.tracker.findByName(name);
        if (result == null) {
            System.out.println("Заявки не найдены, повторите попытку");
        } else {
            System.out.println("------------ Заявки успешно найдена --------------");
            for (Item item : result) {
                System.out.println(item.toString(item.getId(), item.getName(), item.getDescription()));
            }
        }
    }
    /**
     * Метод реализует добавление комментария к заявке по id.
     */
    private void addCommentsById() {
        System.out.println("------------ Добавляние комментария по id --------------");
        String id = this.input.ask("Введите id заявки : ");
        String comment = this.input.ask("Введите комментарий к заявке : ");
        boolean result = this.tracker.addCommentById(id, comment);
        if (result) {
            System.out.println("------------ Комментарий успешно добавлен --------------");
            Item item = this.tracker.findById(id);
            String[] comments = item.getComments();
            System.out.println("------------ Список всех комментариев --------------");
            for (int i = 0; i < comments.length; i++) {
                if (comments[i] != null) {
                    System.out.println(comments[i]);
                }
            }
        } else {
            System.out.println("Комментарий не может быть добавлен, повторите попытку");
        }
    }
    /**
     * Метод реализует показ функций меню.
     */
    private void showMenu() {
        System.out.printf("%s%s%s%s", "Меню.", System.lineSeparator(),
                "Выберите пункт меню :", System.lineSeparator());
        System.out.println("0.Добавить новую заявку");
        System.out.println("1.Показать все заявки");
        System.out.println("2.Редактировать заявку");
        System.out.println("3.Удалить заявку");
        System.out.println("4.Найти заявку по id");
        System.out.println("5.Найти заявки по имени");
        System.out.println("6.Добавить комментарий по id");
        System.out.println("7.Выйти");
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}