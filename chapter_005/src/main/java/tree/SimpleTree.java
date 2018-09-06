package tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E extends Comparable<E>> implements Iterable<E> {
    /**корень дерева*/
    private final Node<E> root;
    /**количество структурных изменений*/
    private int modCount = 0;

    public SimpleTree(Node<E> root) {
        this.root = root;
    }
    /**ищет предка по заданному значению*/
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }
    /**добавляет новую вершну с заданным значение, если этой вершины нет среди детей указанного предка*/
    boolean add(E parent, E childValue) {
        Optional<Node<E>> addToParent = findBy(parent);
        boolean result = false;

        if (addToParent.isPresent() && treeUnContainChildren(childValue)) {
            addToParent.get().add(new Node<>(childValue));
            modCount++;
            result = true;
        }

        return result;
    }

    private boolean getResultAccordingExpression(Predicate<Node<E>> expression) {
        boolean result = true;

        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (expression.test(el)) {
                result = false;
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }

        return result;
    }


    private boolean treeUnContainChildren(E childValue) {
        return getResultAccordingExpression((node) -> !node.unContainsChild(childValue));
    }

    public boolean isBinary() {
        return getResultAccordingExpression((node) -> node.childrenCount() > 2);
    }

    /**
     * итератор осуществляет обход в ширину.
     * для хранения вершин используется очередь.
     * вершина первой приходит и первой уходит(удаляется),
     * но ее дети записываются в конец очереди.
     * */

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final LinkedList<Node<E>> children = new LinkedList<>(Collections.singletonList(root));
            private boolean nextExist = root != null;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return nextExist;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = null;
                if (children.size() != 0) {
                    Node<E> firstOfChildren = children.pollFirst();
                    if (firstOfChildren != null) {
                        children.addAll(firstOfChildren.leaves());
                        result = firstOfChildren.getValue();
                        nextExist = children.size() != 0;
                    }
                }
                return result;
            }

        };
    }
}
