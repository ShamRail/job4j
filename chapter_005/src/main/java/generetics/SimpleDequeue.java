package generetics;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleDequeue<T> implements Iterable<T> {

    private Node<T> top = new Node<>(null);
    private Node<T> bottom = new Node<>(null);
    private int size = 0;

    public int size() {
        return this.size;
    }

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

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Node<T> pointer = top;
            private final int expectedModCount = size;

            @Override
            public boolean hasNext() {
                if (expectedModCount != size) {
                    throw new ConcurrentModificationException();
                }
                return pointer != null;
            }

            @Override
            public T next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }

                T result = this.pointer.value;
                this.pointer = this.pointer.next;
                return result;
            }
        };
    }
}
