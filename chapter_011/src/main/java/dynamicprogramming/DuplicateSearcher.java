package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class DuplicateSearcher {

    public <T> int search(T[] array) {
        Map<T, Integer> table = new HashMap<>();
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            if (table.containsKey(array[i])) {
                table.replace(array[i], table.get(array[i]), table.get(array[i]) + 1);
                result++;
            } else {
                table.put(array[i], 0);
            }
        }
        return result;
    }

}
