package list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleDynamicArrayList<E> implements Iterable<E> {

    private int defaultSize = 10;
    private Object[] store = new Object[defaultSize];
    private int position = 0;
    private int modCount = 0;

    public void add(E data) {
        if (isFull()) {
            this.reSizeStore();
        }
        this.store[position++] = data;
        this.modCount++;
    }

    public int size() {
        return this.position;
    }

    E get(int index) {
        if (this.isIndexIsOutOfBounds(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E) this.store[index];
    }

    public boolean contains(E data) {
        boolean result = false;
        for (int i = 0; i < this.position; i++) {
            if (this.store[i].equals(data)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean isIndexIsOutOfBounds(int index) {
        return index >= this.defaultSize && index < 0;
    }

    private boolean isFull() {
        return position >= defaultSize;
    }

    private void reSizeStore() {
        this.defaultSize *= 2;
        this.store = Arrays.copyOf(this.store, this.defaultSize);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /**cursor potion*/
            private int cursor = -1;
            /**flag of existence next no null element.*/
            private boolean isExistNextElement = this.isExistNext();

            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return this.isExistNextElement;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = (E) store[this.cursor];
                this.isExistNextElement = this.isExistNext();
                return result;
            }

            /**
             * isExistNext.
             * Move cursor to first no null element.
             * If it is done return true else false.
             * */
            private boolean isExistNext() {
                boolean result = false;
                for (int i = cursor + 1; i < store.length; i++) {
                    if (store[i] != null) {
                        this.cursor = i;
                        result = true;
                        break;
                    }
                }
                return result;
            }
        };
    }
}
