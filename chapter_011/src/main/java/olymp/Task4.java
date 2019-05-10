package olymp;

public class Task4 {

    public String calculate(String input, int k) {
        int index;
        int sbi = 0;
        int sei = -1;
        int sNumber = 0;
        char[] s = input.toCharArray();
        for (index = 0; index < s.length && sNumber != k; index++) {
            if (s[index] == '.') {
                if (index + 2 < s.length && s[index + 1] == '.' && s[index + 2] == '.') {
                    index += 2;
                }
                sbi = sei + 1;
                sei = index;
                sNumber++;
            }
        }
        return input.substring(sbi, sei + 1);
    }

}
