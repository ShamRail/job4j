package ru.job4j;

class FindByIdAction implements UserAction {
    public int key() {
        return 4;
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявки по id --------------");
        String id = input.ask("Введите id искомой заявки : ");
        Item result = tracker.findById(id);
        if (result == null) {
            System.out.println("Заявка не найдена, повторите попытку");
        } else {
            System.out.println("------------ Заявка успешно найдена --------------");
            System.out.println(result.toString(result.getName(), result.getDescription()));
        }
    }

    public String info() {
        return "4.Найти заявку по id";
    }
}

class FindByNameAction implements UserAction {
    public int key() {
        return 5;
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Поиск заявки по имени --------------");
        String name = input.ask("Введите имя искомых заявок : ");
        Item[] result = tracker.findByName(name);
        if (result == null) {
            System.out.println("Заявки не найдены, повторите попытку");
        } else {
            System.out.println("------------ Заявки успешно найдена --------------");
            for (Item item : result) {
                System.out.println(item.toString(item.getId(), item.getName(), item.getDescription()));
            }
        }
    }

    public String info() {
        return "5.Найти заявки по имени";
    }
}

class AddCommentAction implements UserAction {
    public int key() {
        return 6;
    }

    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Добавляние комментария по id --------------");
        String id = input.ask("Введите id заявки : ");
        String comment = input.ask("Введите комментарий к заявке : ");
        boolean result = tracker.addCommentById(id, comment);
        if (result) {
            System.out.println("------------ Комментарий успешно добавлен --------------");
            Item item = tracker.findById(id);
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

    public String info() {
        return "6.Добавить комментарий по id";
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
    public void fillActions() {
        this.actions[0] = new AddAction();
        this.actions[1] = new ShowAction();
        this.actions[2] = new MenuTracker.EditAction();
        this.actions[3] = new MenuTracker.DeleteAction();
        this.actions[4] = new FindByIdAction();
        this.actions[5] = new FindByNameAction();
        this.actions[6] = new AddCommentAction();
        this.actions[7] = new MenuTracker.ExitAction();
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
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddAction implements UserAction {
        public int key() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
        }

        public String info() {
            return "0.Добавить новую заявку";
        }
    }
    private class ShowAction implements UserAction {
        public int key() {
            return 1;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Все заявки --------------");
            Item[] items = tracker.findAll();
            for (Item item : items) {
                System.out.println(item.toString(item.getId(), item.getName(), item.getDescription()));
            }
        }

        public String info() {
            return "1.Показать все заявки";
        }
    }

    private static class EditAction implements UserAction {
        public int key() {
            return 2;
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
                System.out.println(item.toString(item.getName(), item.getDescription()));
            } else {
                System.out.println("Заяка не может быть отредактирована, неверный id");
            }
        }

        public String info() {
            return "2.Редактировать заявку";
        }
    }

    private static class DeleteAction implements UserAction {
        public int key() {
            return 3;
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

        public String info() {
            return "3.Удалить заявку";
        }
    }

    private static class ExitAction implements UserAction {
        public int key() {
            return 7;
        }

        public void execute(Input input, Tracker tracker) {

        }

        public String info() {
            return "7.Выйти";
        }
    }
}
