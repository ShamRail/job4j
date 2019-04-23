package arrays;

import java.util.HashSet;
import java.util.Set;

public class UniqueArray {

    public boolean check(char[] array) {
        boolean result = true;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < array.length && result; i++) {
            result = set.add(array[i]);
        }
        return result;
    }

}
