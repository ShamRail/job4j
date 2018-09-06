package generetics;

public class SimpleDequeue<T> {

    private Node<T> top = new Node<>(null);
    private Node<T> bottom = new Node<>(null);
    private int size = 0;

    public void insertFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (size == 0) {
            bottom = newNode;
        } else {
            top.prev = newNode;
        }
        newNode.next = top;
        top = newNode;
        size++;
    }

    public void insertLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (size == 0) {
            top = newNode;
        } else {
            bottom.next = newNode;
            newNode.prev = bottom;
        }
        bottom = newNode;
        size++;
    }

    public T deleteFirst() {
        T result = top.value;
        if (top.next == null) {
            bottom = null;
        } else {
            top.next.prev = null;
        }
        top = top.next;
        size--;
        return result;
    }


    private static class Node<T> {
        Node<T> prev;
        Node<T> next;
        final T value;
        public Node(T value) {
            this.value = value;
        }
    }
}
