package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    /**массив, хранящий переданный массив.*/
    private final int[][] matrix;
    /**индекс строки.*/
    private int row = 0;
    /**индекс столбца.*/
    private int column = 0;

    private Integer cursor;

    public MatrixIterator(int[][] matrix) {
        this.matrix = matrix;
        cursor = moveCursor();
    }

    private Integer moveCursor() {
        Integer result = null;
        if (matrix.length > 0) {
            for (int rowIndex = row; rowIndex < matrix.length; rowIndex++) {
                if (matrix[rowIndex].length > 0 && column < matrix[rowIndex].length) {
                   result = matrix[rowIndex][column];
                   column++;
                   break;
                } else {
                    column = 0;
                    row = rowIndex + 1;
                }
            }
        }
        return result;
    }

    /**
     * hasNext.
     * Проверяет сначало, если элементы в подмассиве,
     * если нет их то проверяет есть следующий подмассив,
     * Если оба условия не выпоняются возвращает false, иначе true.
     * */

    @Override
    public boolean hasNext() {
        return cursor != null;
    }

    /**
     * next.
     * Проверяет есть подмассивы, иначе вызывает исключение.
     * Если подмассивы есть, то
     * Сначало возвращается элемент в подмассиве, если есть,
     * Иначе возвращается первый элемент следующего подмассива
     * @return элемент.
     * */

    @Override
    public Integer next() {
        Integer result;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        result = cursor;
        cursor = moveCursor();
        return result;
    }

}
