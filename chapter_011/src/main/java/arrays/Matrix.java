package arrays;

public class Matrix {

    public void toZero(int[][] matrix) {
        boolean zeroDetected = false;
        for (int i = 0; i < matrix.length && !zeroDetected; i++) {
            for (int j = 0; j < matrix.length && !zeroDetected; j++) {
                if (matrix[i][j] == 0) {
                    for (int column = 0; column < matrix.length; column++) {
                        matrix[i][column] = 0;
                    }
                    for (int row = 0; row < matrix.length; row++) {
                        matrix[row][j] = 0;
                    }
                    zeroDetected = true;
                }
            }
        }
    }

}
