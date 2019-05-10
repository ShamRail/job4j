package olymp;

public class Task6 {

    public int calculate(int[] weights) {
        int c50 = 0;
        int c100 = 0;
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] == 50) {
                c50++;
            } else {
                c100++;
            }
        }
        int full = Math.min(c50, c100);
        c50 -= full;
        c100 -= full;
        return full + c100 + (c50 / 3) + (c50 % 3 != 0 ? 1 : 0);
    }

}
