package olymp;

import java.util.*;

public class Task7 {

    public TreeMap<Integer, Pair> calculate(int[] ways) {
        HashMap<Integer, Integer> set = new HashMap<>();
        TreeMap<Integer, Pair> result = new TreeMap<>();
        for (int i = 0; i < ways.length - 1; i++) {
            if (set.containsKey(ways[i])) {
                int value = set.remove(ways[i]);
                result.put(ways[i], new Pair(value, ways[i + 1]));
            } else {
                if (!set.containsKey(ways[i + 1])) {
                    set.put(ways[i], ways[i + 1]);
                } else {
                    set.put(ways[i], 0);
                }
            }
        }
        set.put(ways[ways.length - 1], 0);
        for (Map.Entry<Integer, Integer> entry : set.entrySet()) {
            result.put(entry.getKey(), new Pair(entry.getValue(), 0));
        }
        return result;
    }

}
