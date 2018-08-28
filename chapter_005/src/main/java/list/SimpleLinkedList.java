package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> extends SimpleArrayList<E> implements Iterable<E> {

    private int modCount = 0;
    private Node<E> top = new Node<>(null);
    private int count = 0;

    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = this.top.next;
        this.top.next = newNode;
        this.count++;
        this.modCount++;
    }

    public E get(int index) {
        if (isOutOfBounds(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<E> result = this.top;
        for (int i = 0; i < index + 1; i++) {
            result = result.next;
        }
        return result.data;
    }

    private boolean isOutOfBounds(int index) {
        return index >= count || index < 0;
    }

    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> pointer = top;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return pointer.next != null;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = this.pointer.next.data;
                this.pointer = this.pointer.next;
                return result;
            }
        };
    }
}
