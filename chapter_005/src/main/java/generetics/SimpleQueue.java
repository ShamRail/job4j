package generetics;

/**Класс реализующий структуры данных очередь.*/

public class SimpleQueue<E> {
    private SimpleDequeue<E> simpleDequeue = new SimpleDequeue<>();

    /**
     * push.
     * Метод, добавляет данные в конец очереди.
     * Если добавляется первый элемент, то начало и конец совпадают.
     * Иначе добавляем в конец.
     * @param data данные, помещаемые в новую ячейку.
     * */

    public void push(E data) {
        simpleDequeue.insertLast(data);
    }

    /**
     * poll.
     * Метод, удаляющий первый элемент из очереди.
     * Если удалять нечего возвращает нуль, иначе данные удаленной ячейки.
     * @return E данные.
     * */

    public E poll() {
        return simpleDequeue.deleteFirst();
    }
}
