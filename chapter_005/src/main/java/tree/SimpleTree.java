package tree;

import java.util.*;

public class SimpleTree<E extends Comparable<E>> implements Iterable<E> {
    /**корень дерева*/
    private Node<E> root;
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

        if (addToParent.isPresent() && addToParent.get().contains(childValue)) {
            addToParent.get().add(new Node<>(childValue));
            modCount++;
            result = true;
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
        return new Iterator<E>() {
            private LinkedList<Node<E>> childs = new LinkedList<>(Collections.singletonList(root));
            private boolean nextExist = root != null;
            int expectedModCount = modCount;

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
                if (childs.size() != 0) {
                    Node<E> firstOfChilds = childs.pollFirst();
                    childs.addAll(firstOfChilds.leaves());
                    result = firstOfChilds.getValue();
                    nextExist = childs.size() != 0;
                }
                return result;
            }

        };
    }
}
