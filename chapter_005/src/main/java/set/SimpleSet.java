package set;

import list.SimpleDynamicArrayList;

public class SimpleSet<E> extends SimpleDynamicArrayList<E> {

    @Override
    public void add(E data) {
        if (!super.contains(data)) {
            super.add(data);
        }
    }
}
