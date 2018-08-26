package generetics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    /**values store.*/
    private Object[] store;
    /**last adding element position.*/
    private int position = 0;
    /**input array size*/
    private final int count;
    /**constructor.*/
    public SimpleArray(int size) {
        this.store = new Object[size];
        this.count = size;
    }
    /**
     * add.
     * @param value adding value.
     * Return true if value added else return false.
     * When add value to full store invoke exception.
     * @return result of adding.
     * @exception ArrayIndexOutOfBoundsException
     * */
    public void add(T value) {
        this.checkIndex(position);
        this.store[position++] = value;
    }
    /**
     * get.
     * If index in bounds of store size return value at index.
     * Else invoke exception.
     * @param index index of getting value.
     * @exception ArrayIndexOutOfBoundsException
     * @return value at index.
     * */
    public T get(int index) {
        this.checkIndex(index);
        return (T) this.store[index];
    }
    /**
     * set.
     * If index in bounds of store size replace value at index.
     * Else invoke exception.
     * @param index of replacing value.
     * @param value new value.
     * @exception ArrayIndexOutOfBoundsException
     * */
    public void set(int index, T value) {
        this.checkIndex(index);
        this.store[index] = value;
    }
    /**
     * delete.
     * If index in bounds of store size delete value at index.
     * Else invoke exception.
     * Deleting according to rewrite all elements from right of index to index.
     * Last elements become null.
     * @param index index of deleting value.
     * @exception ArrayIndexOutOfBoundsException
     * */
    public void delete(int index) {
        this.checkIndex(index);

        for (int i = index; i < position - 1; i++) {
            this.store[i] = this.store[i + 1];
        }
        this.store[position - 1] = null;
        position--;
    }
    /**
     * checkIndex.
     * If index in bounds of store size do anything.
     * Else invoke exception.
     * @param index checking index.
     * @exception ArrayIndexOutOfBoundsException
     * */
    private void checkIndex(int index) {
        if (index >= count || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            /**cursor potion*/
            private int cursor = -1;
            /**flag of existence next no null element.*/
            private boolean isExistNextElement = this.isExistNext();

            @Override
            public boolean hasNext() {
                return this.isExistNextElement;
            }

            @Override
            public T next() {
                if (!isExistNextElement) {
                    throw new NoSuchElementException();
                }
                T result = (T) store[this.cursor];
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
