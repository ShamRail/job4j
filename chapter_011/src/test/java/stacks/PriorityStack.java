package stacks;

import java.util.*;
import java.util.function.BiPredicate;

public class PriorityStack<T> {

    private Node<T> head = new Node<>(null);

    private Node<T> tail = new Node<>(null);

    private Comparator<T> comparator;

    public PriorityStack(Comparator<T> comparator) {
        this.comparator = comparator;
        this.head.next = tail;
        this.tail.prev = head;
    }

    public void offer(T elem) {
        Node<T> node = new Node<>(elem);
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public T peek() {
        return head.next.data;
    }

    public T poll() {
        T result = head.next.data;
        head.next = head.next.next;
        head.next.prev = head;
        return result;
    }

    public T min() {
        return retrieve((el1, el2) -> comparator.compare(el1, el2) < 0);
    }

    public T max() {
        return retrieve((el1, el2) -> comparator.compare(el1, el2) > 0);
    }

    private T retrieve(BiPredicate<T, T> condition) {
        T result = null;
        if (head.next != null) {
            Node<T> current = head.next;
            result = current.data;
            while (current.next != null) {
                if (condition.test(current.data, result)) {
                    result = current.data;
                }
                current = current.next;
            }
        }
        return result;
    }

    private static class Node<T> {
        Node<T> prev;
        Node<T> next;
        T data;

        Node(T data) {
            this.data = data;
        }

    }

}
