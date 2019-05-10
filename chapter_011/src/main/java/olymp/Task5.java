package olymp;

public class Task5 {

    public int calculate(int first, int second) {
        int further = (depth(first) > depth(second)) ? first : second;
        int near = (depth(first) <= depth(second)) ? first : second;
        while (depth(further) != depth(near)) {
            further /= 2;
        }
        while (further != near) {
            further /= 2;
            near /= 2;
        }
        return near;
    }

    private int depth(int vNum) {
        return (int) (Math.log10(vNum) / Math.log10(2.0));
    }

}
