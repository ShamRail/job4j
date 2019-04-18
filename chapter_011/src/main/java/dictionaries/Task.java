package dictionaries;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Task {

    public int maxSubtraction(int[] array) {
        return computeValue(fillMap(array));
    }

    private Map<Integer, LinkedList<Integer>> fillMap(int[] array) {
        Map<Integer, LinkedList<Integer>> table = new LinkedHashMap<>();
        for (int key = 0; key < array.length; key++) {
            if (table.containsKey(array[key])) {
                table.get(array[key]).add(key);
            } else {
                LinkedList<Integer> indexes = new LinkedList<>();
                indexes.add(key);
                table.put(array[key], indexes);
            }
        }
        return table;
    }

    private int computeValue(Map<Integer, LinkedList<Integer>> table) {
        int result = 0;
        for (Map.Entry<Integer, LinkedList<Integer>> entry : table.entrySet()) {
            LinkedList<Integer> indexes = entry.getValue();
            if (indexes.size() > 1) {
                int sub = indexes.getLast() -  indexes.getFirst();
                result = sub > result ? sub : result;
            }
        }
        return result;
    }

}
