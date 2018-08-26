package generetics;

import java.util.Iterator;

public abstract class AbstractStore {

    /**
     * findIndexByValue.
     * */

    protected int findIndexByValue(SimpleArray<? extends Base> store, String id) {
        int result = -1;
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }

    protected boolean delete(SimpleArray<? extends Base> store, String id) {
        boolean result = false;
        int indexById = this.findIndexByValue(store, id);
        if (indexById != -1) {
            store.delete(indexById);
            result = true;
        }
        return result;
    }

    protected Object findById(SimpleArray<? extends Base> store, String id) {
        Object result = null;
        Iterator<?> iterator = store.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            Base base = (Base) next;
            if (base.getId().equals(id)) {
                result = next;
                break;
            }
        }
        return result;
    }

}
