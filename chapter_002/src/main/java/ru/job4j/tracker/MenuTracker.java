package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

class FindByIdAction extends BaseAction {

    public FindByIdAction(int key, String name) {
        super(key, name);
    }
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявки по id --------------");
        String id = input.ask("Введите id искомой заявки : ");
        Item result = tracker.findById(id);
        if (result == null) {
            System.out.println("Заявка не найдена, повторите попытку");
        } else {
            System.out.println("------------ Заявка успешно найдена --------------");
            System.out.println(result.toString());
        }
    }
}

class FindByNameAction extends BaseAction {

    public FindByNameAction(int key, String name) {
        super(key, name);
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявки по имени --------------");
        String name = input.ask("Введите имя искомых заявок : ");
        ArrayList<Item> result = tracker.findByName(name);
        if (result == null) {
            System.out.println("Заявки не найдены, повторите попытку");
        } else {
            System.out.println("------------ Заявки успешно найдена --------------");
            for (Item item : result) {
                System.out.println(item.toString());
            }
        }
    }
}

class AddCommentAction extends BaseAction {

    public AddCommentAction(int key, String name) {
        super(key, name);
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Добавляние комментария по id --------------");
        String id = input.ask("Введите id заявки : ");
        String comment = input.ask("Введите комментарий к заявке : ");
        boolean result = tracker.addCommentById(id, comment);
        if (result) {
            System.out.println("------------ Комментарий успешно добавлен --------------");
            Item item = tracker.findById(id);
            ArrayList<String> comments = item.getComments();
            System.out.println("------------ Список всех комментариев --------------");
            for (String string : comments) {
                System.out.println(string);
            }
        } else {
            System.out.println("Комментарий не может быть добавлен, повторите попытку");
        }
    }
}

public class MenuTracker {
    /**
     * @param хранит ссылку на объект .
     */
    private Input input;
    /**
     * @param хранит ссылку на объект .
     */
    private Tracker tracker;
    /**
     * @param хранит ссылку на массив типа UserAction.
     */
    private UserAction[] actions = new UserAction[8];

    /**
     * Конструктор.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions(StartUI ui) {
        this.actions[0] = new AddAction(0, "Добавить новую заявку");
        this.actions[1] = new ShowAction(1, "Показать все заявки");
        this.actions[2] = new MenuTracker.EditAction(2, "Редактировать заявку");
        this.actions[3] = new MenuTracker.DeleteAction(3, "Удалить заявку");
        this.actions[4] = new FindByIdAction(4, "Найти заявку по id");
        this.actions[5] = new FindByNameAction(5, "Найти заявки по имени");
        this.actions[6] = new AddCommentAction(6, "Добавить комментарий по id");
        this.actions[7] = new MenuTracker.ExitAction(ui);
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        Arrays.stream(this.actions).filter(Objects::nonNull).forEach((action) -> System.out.println(action.info()));
    }
    /**
     * fillMenuRange.
     * Метод, возвращающий диапозон, доступных пунктов меню.
     * @return диапозон.
     * */
    public int[] fillMenuRange() {
        int[] range = new int[this.actions.length];
        for (int i = 0; i < this.actions.length; i++) {
            range[i] = i;
        }
        return range;
    }

    private class AddAction extends BaseAction {

        public AddAction(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
        }


    }

    private class ShowAction extends BaseAction {

        public ShowAction(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Все заявки --------------");
            ArrayList<Item> items = tracker.findAll();
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }
    }

    private static class EditAction extends BaseAction {

        public EditAction(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Редактирование новой заявки --------------");
            String id = input.ask("Введите id редактируемой заявки : ");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            if (tracker.findById(id) != null) {
                tracker.replace(id, item);
                System.out.println("------------ Заявка успешно отредактирована --------------");
                System.out.println(item.toString());
            } else {
                System.out.println("Заяка не может быть отредактирована, неверный id");
            }
        }
    }

    private static class DeleteAction extends BaseAction {

        public DeleteAction(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите id удаляемой заявки : ");
            boolean result = tracker.delete(id);
            if (!result) {
                System.out.println("Неверное id, повторите попытку");
            } else {
                System.out.println("------------ Заявка успешно удалена --------------");
            }
        }
    }

    private static class ExitAction implements UserAction {
        StartUI ui;
        public ExitAction(StartUI ui) {
            this.ui = ui;
        }

        public int key() {
            return 7;
        }

        public void execute(Input input, Tracker tracker) {
            ui.stop();
        }

        public String info() {
            return "7.Выйти";
        }
    }
}
