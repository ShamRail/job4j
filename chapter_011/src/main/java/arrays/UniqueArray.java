package arrays;

public class UniqueArray {

    public boolean check(char[] array) {
        boolean result = true;
        for (int i = 1; i < array.length && result; i++) {
            for (int j = i - 1; j >= 0 && result; j--) {
                result = array[i] != array[j];
            }
        }
        return result;
    }

}
