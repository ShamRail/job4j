package olymp;

public class Task2 {

    public Pair calculate(int a, int b, int c) {
        int x = c - a - b;
        int y = 0;
        while (a + x > 150 || b + y > 150) {
            x--;
            y++;
        }
        return new Pair(x, y);
    }

}
