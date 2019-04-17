package arrays;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class MatrixTest {

    @Test
    public void whenHaveZero() {
        int[][] data = {
                {1, 2, 3},
                {4, 0, 5},
                {6, 7, 8}
        };
        int[][] expected = {
                {1, 0, 3},
                {0, 0, 0},
                {6, 0, 8}
        };
        Matrix matrix = new Matrix();
        matrix.toZero(data);
        Assert.assertThat(data, Is.is(expected));
    }

    @Test
    public void whenNotHaveZero() {
        int[][] data = {
                {1, 2, 3},
                {4, 5, 5},
                {6, 7, 8}
        };
        int[][] expected = {
                {1, 2, 3},
                {4, 5, 5},
                {6, 7, 8}
        };
        Matrix matrix = new Matrix();
        matrix.toZero(data);
        Assert.assertThat(data, Is.is(expected));
    }

}