package synchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final HashMap<Integer, Integer> userStorage = new HashMap<>();

    public synchronized boolean add(User user) {
        boolean result = false;
        if (!userStorage.containsKey(user.getId())) {
            userStorage.put(user.getId(), user.getAmount());
            result = true;
        }
        return result;
    }

    public synchronized boolean update(User user) {
        return userStorage.containsKey(user.getId())
                && userStorage.replace(user.getId(), userStorage.get(user.getId()), user.getAmount());
    }

    public synchronized boolean delete(User user) {
        return userStorage.containsKey(user.getId())
                && userStorage.remove(user.getId(), user.getAmount());
    }

    public synchronized boolean transfer(int senderId, int receiverId, int amount) {
        boolean result = false;
        if (userStorage.containsKey(senderId) && (userStorage.get(senderId) - amount >= 0)
                && userStorage.containsKey(receiverId)) {
            userStorage.replace(senderId, userStorage.get(senderId) - amount);
            userStorage.replace(receiverId, userStorage.get(receiverId) + amount);
            result = true;
        }
        return result;
    }

    public synchronized int getById(int userId) {
        return userStorage.getOrDefault(userId, -1);
    }

    public synchronized int getCount() {
        return userStorage.size();
    }
}
