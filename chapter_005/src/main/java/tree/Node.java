package tree;

import java.util.ArrayList;
import java.util.List;

public class Node<E extends Comparable<E>> {
    private final List<Node<E>> children = new ArrayList<>();
    private final E value;

    public Node(final E value) {
        this.value = value;
    }

    public void add(Node<E> child) {
        this.children.add(child);
    }

    public List<Node<E>> leaves() {
        return this.children;
    }

    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;

    }

    public boolean unContainsChild(E childValue) {
        return children.stream().noneMatch((value) -> value.value.equals(childValue));
    }


    public E getValue() {
        return this.value;
    }

    public int childrenCount() {
        return children.size();
    }
}
