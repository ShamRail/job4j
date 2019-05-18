package graphs;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.LinkedList;

public class MinWayTest {

    private final static int INF = Integer.MAX_VALUE;

    @Test
    public void test() {
        int[][] graph = {
                            {0, 1, 4, INF, 2, INF},
                            {1, 0, INF, INF, 9, INF},
                            {4, INF, 0, 7, INF, INF},
                            {INF, INF, 7, 0, INF, 2},
                            {2, 9, INF, INF, 0, 8},
                            {INF, INF, INF, 2, 8, 0}
        };
        MinWay minWay = new MinWay();
        LinkedList<Integer> result = minWay.restoreWay(graph, 0, 5);
        LinkedList<Integer> expect = new LinkedList<>(Arrays.asList(0, 4, 5));
        Assert.assertThat(result, Is.is(expect));
    }

}