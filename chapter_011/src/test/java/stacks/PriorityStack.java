package stacks;

import java.util.*;

public class PriorityStack<T> {

    private LinkedList<T> stack = new LinkedList<>();

    private Comparator<T> comparator;

    public PriorityStack(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void offer(T elem) {
        if (stack.isEmpty()) {
            stack.addFirst(elem);
        } else {
            T head = stack.pollFirst();
            if (comparator.compare(head, elem) > 0) {
                stack.addFirst(elem);
                stack.addFirst(head);
            } else {
                stack.addFirst(head);
                stack.addFirst(elem);
            }
        }
    }

    public T peek() {
        return stack.peekFirst();
    }

    public T poll() {
        return stack.pollFirst();
    }

}
