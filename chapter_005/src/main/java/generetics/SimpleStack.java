package generetics;

/**Класс реализующий структуры данных стек.
 * @see list.SimpleArrayList
 * */

public class SimpleStack<E> {

    private SimpleDequeue<E> simpleDequeue = new SimpleDequeue<>();

    /**
     * push.
     * Метод, добавляет данные в конец стека.
     * Если добавляется первый элемент, то начало и конец совпадают.
     * Иначе добавляем в конец.
     * @param data данные, помещаемые в новую ячейку.
     * */

    public void push(E data) {
        simpleDequeue.insertFirst(data);
    }

    /**
     * poll.
     * Метод, удаляющий последний элемент из стека.
     * Если удалять нечего возвращает нуль, иначе данные удаленной ячейки.
     * @return E данные.
     * */

    public E poll() {
        return simpleDequeue.deleteFirst();
    }

    public boolean isEmpty() {
        return simpleDequeue.size() == 0;
    }
}
