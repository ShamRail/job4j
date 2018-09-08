package additionalquestions;

import java.util.HashMap;
import java.util.List;

public class Store {

    public Info getDifferenceInfo(List<User> previous, List<User> current) {
        Info result = new Info();
        HashMap<Integer, String> currentAsMap = copyToMap(current);
        for (User user : previous) {
            if (!currentAsMap.containsKey(user.id)) {
                result.increaseDeleted();
            } else {
                if (valueIsChanged(user.name, currentAsMap.get(user.id))) {
                    result.increaseChanged();
                }
            }
        }
        result.setAddedCount(Math.abs(current.size() - previous.size()  - result.getDeletedCount()));
        return result;
    }

    private boolean valueIsChanged(String firstUserName, String secondUserName) {
        return firstUserName != null && !firstUserName.equals(secondUserName);
    }

    private HashMap<Integer, String> copyToMap(List<User> list) {
        HashMap<Integer, String> result = new HashMap<>();
        list.forEach((user -> result.put(user.id, user.name)));
        return result;
    }

    static class User {
        private final int id;
        private final String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}

