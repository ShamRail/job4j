package generetics;

import java.util.Iterator;

public abstract class  AbstractStore<E extends Base> implements Store<E> {

    private SimpleArray<E> store = new SimpleArray<>(10);

    @Override
    public void add(E model) {
        this.store.add(model);
    }

    @Override
    public boolean replace(String id, E model) {
        boolean result = false;
        int indexById = this.findIndexByValue(id);
        if (indexById != -1) {
            this.store.set(indexById, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int indexById = this.findIndexByValue(id);
        if (indexById != -1) {
            store.delete(indexById);
            result = true;
        }
        return result;
    }

    @Override
    public E findById(String id) {
        E result = null;
        Iterator<E> iterator = store.iterator();
        while (iterator.hasNext()) {
            E next = iterator.next();
            Base base = (Base) next;
            if (base.getId().equals(id)) {
                result = next;
                break;
            }
        }
        return result;
    }

    private int findIndexByValue(String id) {
        int result = -1;
        for (int i = 0; i < this.store.size(); i++) {
            if (this.store.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }
}