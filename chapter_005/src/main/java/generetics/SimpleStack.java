package generetics;
import list.SimpleArrayList;

/**Класс реализующий структуры данных стек.
 * @see list.SimpleArrayList
 * */

public class SimpleStack<E> extends SimpleArrayList<E> {

    /**
     * push.
     * Метод, добавляет данные в конец стека.
     * Если добавляется первый элемент, то начало и конец совпадают.
     * Иначе добавляем в конец.
     * @param data данные, помещаемые в новую ячейку.
     * */

    public void push(E data) {
        super.add(data);
    }

    /**
     * poll.
     * Метод, удаляющий последний элемент из стека.
     * Если удалять нечего возвращает нуль, иначе данные удаленной ячейки.
     * @return E данные.
     * */

    public E poll() {
        return super.delete();
    }
}
