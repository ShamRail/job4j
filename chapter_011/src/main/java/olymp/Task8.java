package olymp;

import sortings.BubbleSort;
import java.util.*;

public class Task8 {

    public int calculate(int[] points, int exK, TreeSet<Integer> exceptedPoints) {
        int result = 0;
        ArrayList<Pair> sortedPoints = sort(points);
        for (int i = 0; i < exK; i++) {
            int min;
            int maxMin = Integer.MIN_VALUE;
            int maxMinI = -1;
            for (int j = 0; j < sortedPoints.size(); j++) {
                min = min(sortedPoints, j);
                if (min > maxMin) {
                    maxMin = min;
                    maxMinI = j;
                }
            }
            exceptedPoints.add(sortedPoints.get(maxMinI).getX());
            sortedPoints.remove(maxMinI);
            result = maxMin;
        }
        return result;
    }

    private ArrayList<Pair> sort(int[] points) {
        Pair[] sorted = new Pair[points.length];
        for (int i = 0; i < points.length; i++) {
            sorted[i] = new Pair(i + 1, points[i]);
        }
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(sorted, Comparator.comparingInt(Pair::getY));
        return new ArrayList<>(Arrays.asList(sorted));
    }

    private int min(ArrayList<Pair> sortedPoints, int exI) {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < sortedPoints.size() - 1; k++) {
            if (k != exI) {
                int next = (k + 1 != exI) ? k + 1 : (k + 2 < sortedPoints.size() ? k + 2 : -1);
                if (next != -1 && sortedPoints.get(next).getY() - sortedPoints.get(k).getY() < min) {
                    min = sortedPoints.get(next).getY() - sortedPoints.get(k).getY();
                }
            }
        }
        return min;
    }

}
