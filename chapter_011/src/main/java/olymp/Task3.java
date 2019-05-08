package olymp;

import java.util.LinkedList;
import java.util.List;

public class Task3 {

    public List<Integer> calculate(int[] array) {
        List<Integer> result = new LinkedList<>();
        int count = 1;
        for (int i = 0; i < array.length; i++) {
            if ((i + 1 < array.length) && array[i] == array[i + 1]) {
                count++;
            } else {
                result.add(count);
                result.add(array[i]);
                count = 1;
            }
        }
        return result;
    }

}
