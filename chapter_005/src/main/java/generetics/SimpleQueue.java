package generetics;

/**Класс реализующий структуры данных очередь.*/

public class SimpleQueue<E> {
    /**начало очереди.*/
    private Node<E> top = null;
    /**конец очереди.*/
    private Node<E> bottom = null;
    /**размер очереди.*/
    private int size = 0;

    /**
     * push.
     * Метод, добавляет данные в конец очереди.
     * Если добавляется первый элемент, то начало и конец совпадают.
     * Иначе добавляем в конец.
     * @param data данные, помещаемые в новую ячейку.
     * */

    public void push(E data) {
        Node<E> newNode = new Node<>(data);
        if (top == null) {
            top = newNode;
        } else {
            bottom.next = newNode;
        }
        bottom = newNode;
        this.size++;
    }

    /**
     * poll.
     * Метод, удаляющий первый элемент из очереди.
     * Если удалять нечего возвращает нуль, иначе данные удаленной ячейки.
     * @return E данные.
     * */

    public E poll() {
        E result = null;
        if (size != 0) {
            result = top.data;
            top = top.next;
            if (top == null) {
                 bottom = null;
            }
            this.size--;
        }
        return result;
    }

    /**Класс ячейка.*/

    private static class Node<E> {
        Node<E> next;
        Node<E> previous;
        E data;

        public Node(E data) {
            this.data = data;
        }
    }
}
