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
        for (E value : this) {
            if (expression.test(findBy(value).get())) {
                result = false;
                break;
            }
        }
        return result;
    }


    private boolean treeUnContainChildren(E childValue) {
        return getResultAccordingExpression((node) -> !node.unContainsChild(childValue));
    }

    public boolean isBinary() {
       boolean result = true;
        TreeIterator<E> treeIterator = (TreeIterator<E>) this.iterator();
        while (treeIterator.hasNext()) {
            if (!treeIterator.isAccordChildCount(2)) {
                result = false;
                break;
            }
            treeIterator.next();
        }
        return result;
    }


    /**
     * итератор осуществляет обход в ширину.
     * для хранения вершин используется очередь.
     * вершина первой приходит и первой уходит(удаляется),
     * но ее дети записываются в конец очереди.
     * */

    @Override
    public Iterator<E> iterator() {
        return new TreeIterator<E>() {
            private final LinkedList<Node<E>> children = new LinkedList<>(Collections.singletonList(root));
            private boolean nextExist = root != null;
            private final int expectedModCount = modCount;
            private int childCount = (root != null) ? root.leaves().size() : -1;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return nextExist;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = null;
                if (children.size() != 0) {
                    Node<E> firstOfChildren = children.pollFirst();
                    if (firstOfChildren != null) {
                        children.addAll(firstOfChildren.leaves());
                        result = firstOfChildren.getValue();
                        childCount = firstOfChildren.leaves().size();
                        nextExist = children.size() != 0;
                    }
                }
                return result;
            }

            @Override
            public boolean isAccordChildCount(int children) {
                return childCount <= children;
            }

        };
    }
}
