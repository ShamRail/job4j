package arrays;

public class PermArray {

    public boolean check(char[] word1, char[] word2) {
        boolean result = word1.length == word2.length;
        if (result) {
            long sumCodes1 = 0;
            long sumCodes2 = 0;
            for (int i = 0; i < word1.length; i++) {
                sumCodes1 += Math.pow(Character.getNumericValue(word1[i]), 2);
                sumCodes2 += Math.pow(Character.getNumericValue(word2[i]), 2);
            }
            result = sumCodes1 == sumCodes2;
        }
        return result;
    }

}
