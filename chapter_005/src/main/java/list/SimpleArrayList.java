package list;

public class SimpleArrayList<E> {

    private Node<E> top = new Node<>(null);
    private int count = 0;

    /**add new element.*/

    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = this.top.next;
        this.top.next = newNode;
        this.count++;
    }

    /**return size of list.*/

    public int size() {
        return this.count;
    }

    /**return element at index if it exist else return null.*/

    public E get(int index) {
        Node<E> result = this.top;
        for (int i = 0; i < index + 1; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * delete first element or null if list is empty.
     * */

    public E delete() {
        E deleted = null;
        if (this.isListNotEmpty()) {
            deleted = this.top.next.data;
            if (this.size() > 1) {
                this.top.next = this.top.next.next;
            } else {
                this.top.next = null;
            }
            this.count--;
        }
        return deleted;
    }

    /**
     * delete last element or null if list is empty.
     * */

    public E deleteLast() {
        E result = null;
        if (this.isListNotEmpty()) {
            Node<E> pointer = this.top;
            while (pointer.next.next != null) {
                pointer = pointer.next;
            }
            result = pointer.next.data;
            pointer.next = pointer.next.next;
            this.count--;
        }
        return result;
    }

    private boolean isListNotEmpty() {
        return this.count != 0;
    }

    /**class - cell.*/

    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }
}
