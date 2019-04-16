package arrays;

public class PermArray {

    public boolean check(char[] word1, char[] word2) {
        boolean result = word1.length == word2.length;
        if (result) {
            boolean[] marked = new boolean[word1.length];
            int markedCount = 0;
            for (int i = 0; i < word1.length; i++) {
                for (int j = 0; j < word2.length; j++) {
                    if (word1[i] == word2[j] && !marked[i]) {
                        marked[i] = true;
                        markedCount++;
                        break;
                    }
                }
            }
            result = markedCount == marked.length;
        }
        return result;
    }

}
