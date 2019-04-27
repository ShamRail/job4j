package queues;

import java.util.Comparator;

public class PriorityQueue<T> {

    private Object[] heap;

    private int capacity;

    private int size;

    private Comparator<T> comparator;;

    public PriorityQueue(Comparator<T> comparator) {
        capacity = 16;
        size = 0;
        heap = new Object[capacity];
        this.comparator = comparator;
    }

    public T peek() {
        return (size == 0) ? null : (T) heap[0];
    }

    public T poll() {
        T result = null;
        if (size > 0) {
            result = (T) heap[0];
            heap[0] = heap[size - 1];
            heap[size - 1] = null;
            size--;
            downOrdering();
        }
        return result;
    }

    public void offer(T elem) {
        resize();
        heap[size++] = elem;
        upOrdering();
    }

    private void downOrdering() {
        int parent = 0;
        int left;
        int right;
        boolean stop;
        if (size > 0) {
            do {
                stop = true;
                left = 2 * parent + 1;
                right = 2 * parent + 2;
                left = (left >= size) ? parent : left;
                right = (right >= size) ? parent : right;
                if (comparator.compare((T) heap[left], (T) heap[parent]) > 0) {
                    exchage(left, parent);
                    parent = left;
                    stop = false;
                } else if (comparator.compare((T) heap[right], (T) heap[parent]) > 0) {
                    exchage(right, parent);
                    parent = right;
                    stop = false;
                }
            } while (!stop);
        }
    }

    private void upOrdering() {
        int parent;
        int child = size - 1;
        boolean stop;
        do {
            stop = true;
            parent = (child - 1) / 2;
            if (comparator.compare((T) heap[child], (T) heap[parent]) > 0) {
                exchage(child, parent);
                child = parent;
                stop = false;
            }
        } while (!stop);
    }

    private void resize() {
        if (size == capacity) {
            capacity *= 2;
            Object[] newArray = new Object[capacity];
            System.arraycopy(heap, 0, newArray, 0, size);
            heap = newArray;

        }
    }

    private void exchage(int first, int second) {
        Object temp = heap[first];
        heap[first] = heap[second];
        heap[second] = temp;
    }

}
