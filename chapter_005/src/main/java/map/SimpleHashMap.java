package map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Entry<K, V>> {
    /**массив записей.*/
    private Entry<K, V>[] table;
    /**размер по умолчаниею.*/
    private int defaultSize = 16;
    /**количество элементов.*/
    private int count = 0;

    private int modCount = 0;

    public SimpleHashMap() {
        this.table = new Entry[this.defaultSize];
    }
    /**
     * метод добавления.
     * Сначало проверяет не переполнилась ли таблица, при необходимости увеличивает ее.
     * Если на добавляемом месте нет еще никаких ячеек, то добвляет ее первой в список.
     * Иначе ищет совпадение по ключу, если находит, перезаписывает значение, иначе добавляет элемент в список.
     * */
    public boolean insert(K key, V value) {

        if (tableIsFillOut()) {
            resizeTable();
        }

        boolean result = false;
        Entry<K, V> newEntry = new Entry<>(key, value);
        int indexInArray = this.hash(key);

        if (this.bucketAtIndexIsEmpty(indexInArray)) {
            table[indexInArray] = newEntry;
            this.count++;
            this.modCount++;
            result = true;
        } else {
            Entry<K, V> first = table[indexInArray];
            boolean isNotChanged = true;
            while (first != null) {
                if (keysEquals(first, newEntry)) {
                    isNotChanged = replaceValues(first, newEntry);
                    result = true;
                    break;
                }
                first = first.next;
            }
            if (isNotChanged) {
                newEntry.next = table[indexInArray];
                table[indexInArray] = newEntry;
                this.count++;
                this.modCount++;
                result = true;
            }

        }
        return result;
    }

    /**
     * метод удаления элемента.
     * возвращает true/false, если элемент удален/ не удален.
     * если удаляется первый элемент, то связывает ячейку массива со следующем элементом.
     * если удаляется не первый элемент, то удаление происходит как в связном списке.
     * */

    public boolean delete(K key) {
        boolean result = false;

        int indexOfElement = hash(key);
        if (!bucketAtIndexIsEmpty(indexOfElement)) {
            Entry<K, V> first = table[indexOfElement];
            Entry<K, V> previous;

            if (keysEquals(first, key)) {
                table[indexOfElement] = first.next;
                this.count--;
                this.modCount++;
                result = true;
            }

            while (!result && first.next != null) {
                previous = first;
                first = first.next;
                if (keysEquals(first, key)) {
                    previous.next = first.next;
                    this.count--;
                    this.modCount++;
                    result = true;
                    break;
                }
            }

        }
        return result;
    }

    /**
     * увеличивает размер хэш-таблицы, перехэшируя значения и перераспределяя элементы.
     * */
    private void resizeTable() {
        int lastSize = defaultSize;
        defaultSize *= 2;
        Entry<K, V>[] newTable = new Entry[defaultSize];

        for (int i = 0; i < lastSize; i++) {
            if (!bucketAtIndexIsEmpty(i)) {
                Entry<K, V> first = this.table[i];

                while (first != null) {
                    int hashOfFirst = hash(first.key);
                    if (newTable[hashOfFirst] == null) {
                        Entry<K, V> newEntry = new Entry<>(first.key, first.value);
                        newTable[hashOfFirst] = newEntry;
                    } else {
                        addNewEntryToEnding(newTable[hashOfFirst], first);
                    }
                    first = first.next;
                }

            }
        }

        this.table = newTable;
    }
    /**
     * проверяет не переполнена ли таблица.
     * */
    private boolean tableIsFillOut() {
        return this.count >= this.defaultSize;
    }
    /**
     * проверяет ключи на эквивалентность.
     * */
    private boolean keysEquals(Entry<K, V> first, Entry<K, V> newEntry) {
        return (first.key == null && newEntry.key == null)
                || (first.key != null && first.key.equals(newEntry.key));
    }

    private boolean keysEquals(Entry<K, V> first, K key) {
        return (first.key == null && key == null)
                || (first.key != null && first.key.equals(key));
    }
    /**
     * меняет значения местами.
     * */
    private boolean replaceValues(Entry<K, V> first, Entry<K, V> newEntry) {
        first.value = newEntry.value;
        return false;
    }

    private void addNewEntryToEnding(Entry<K, V> firstInNewTable, Entry<K, V> first) {
        while (firstInNewTable.next != null) {
            firstInNewTable = firstInNewTable.next;
        }
        firstInNewTable.next = first;
    }
    /**
     * метод получения.
     * достает элемент, по очередно сравнивая по ключу все элемента в списке bucket'a.
     * */
    public V get(K key) {
        int indexOfGettingEntry = this.hash(key);
        V result = null;

        if (!bucketAtIndexIsEmpty(indexOfGettingEntry)) {
            Entry<K, V> first = table[indexOfGettingEntry];

            while (first != null) {
                if (keysEquals(first, key)) {
                    result = first.value;
                    break;
                }
                first = first.next;
            }
        }

        return result;
    }
    /**
     * проверяет bucket на пустоту.
     * */
    private boolean bucketAtIndexIsEmpty(int indexInArray) {
        return this.table[indexInArray] == null;
    }
    /**
     * добавляет элемент первым в bucket.
     * */

    private int hash(K key) {
        return (key == null) ? 0 : key.hashCode() & (defaultSize - 1);
    }

    public int size() {
        return this.count;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {

            private int index = -1;
            private int expectedModCount = modCount;
            private Entry<K, V> cursor = goToFirstNoEmptyElement();

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public Entry<K, V> next() {
                Entry<K, V> result = cursor;

                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (nextElementInBucketListExist()) {
                    moveCursorToNextElementInBucketList();
                } else {
                    cursor = goToFirstNoEmptyElement();
                }
                return result;
            }

            private void moveCursorToNextElementInBucketList() {
                cursor = cursor.next;
            }

            private boolean nextElementInBucketListExist() {
                return cursor.next != null;
            }

            private Entry<K, V> goToFirstNoEmptyElement() {
                Entry<K, V> result = null;
                for (int i = this.index + 1; i < defaultSize; i++) {
                    if (!bucketAtIndexIsEmpty(i)) {
                        result = table[i];
                        this.index = i;
                        break;
                    }
                }
                return result;
            }
        };
    }

    public static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
