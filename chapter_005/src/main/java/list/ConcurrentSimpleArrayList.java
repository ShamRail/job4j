package list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class ConcurrentSimpleArrayList<E> implements Iterable<E> {

    @GuardedBy("this")
    private final SimpleDynamicArrayList<E> list = new SimpleDynamicArrayList<>();

    public synchronized void add(E data) {
        this.list.add(data);
    }

    public synchronized int size() {
        return this.list.size();
    }

    public synchronized E get(int index) {
        return this.list.get(index);
    }

    public synchronized boolean contains(E data) {
        return this.list.contains(data);
    }

    @Override
    public Iterator<E> iterator() {
        return this.copy(this.list).iterator();
    }

    private synchronized SimpleDynamicArrayList<E> copy(SimpleDynamicArrayList<E> arrayList) {
        SimpleDynamicArrayList<E> newArrayList = new SimpleDynamicArrayList<>();
        for (E value : arrayList) {
            newArrayList.add(value);
        }
        return newArrayList;
    }
}
