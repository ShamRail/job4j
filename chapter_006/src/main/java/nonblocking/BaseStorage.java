package nonblocking;

import java.util.concurrent.ConcurrentHashMap;

public class BaseStorage {

    private final ConcurrentHashMap<Integer, Base> storage = new ConcurrentHashMap<>();

    public void add(Base base) {
        storage.putIfAbsent(base.getId(), base);
    }

    public void update(Base base) throws OptimisticException {
        storage.computeIfPresent(base.getId(), (id, base1) -> {
            if (storage.get(id).getVersion() != base.getVersion()) {
                throw new OptimisticException();
            }
            base.increaseVersion();
            return base;
        });
    }

    public void delete(Base base) {
        if (storage.contains(base)) {
            storage.remove(base.getId(), base);
        }
    }

    public Base getById(int id) {
        return storage.get(id);
    }

}
