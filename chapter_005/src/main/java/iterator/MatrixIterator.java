package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator {
    /**массив, хранящий переданный массив.*/
    private int[][] multiArray;
    /**индекс подмассива.*/
    private int subArrayIndex = 0;
    /**индекс элемента в подмассиве.*/
    private int indexOfValueInSubArray = 0;

    public MatrixIterator(int[][] multiArray) {
        this.multiArray = multiArray;
    }


    /**
     * hasNext.
     * Проверяет сначало, если элементы в подмассиве,
     * если нет их то проверяет есть следующий подмассив,
     * Если оба условия не выпоняются возвращает false, иначе true.
     * @param result
     * */
    @Override
    public boolean hasNext() {
        return indexOfValueInSubArray < multiArray[subArrayIndex].length
                || indexOfNoEmptySubArray() != -1;
    }

    /**
     * indexOfNoEmptySubArray.
     * Возвращает элемент первого не пустого подмассива.
     * Иначе -1.
     * @return результат.
     * */

    private int indexOfNoEmptySubArray() {
        int result = -1;
        for (int i = subArrayIndex + 1; i < multiArray.length; i++) {
            if (multiArray[i].length != 0) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * next.
     * Проверяет есть подмассивы, иначе вызывает исключение.
     * Если подмассивы есть, то
     * Сначало возвращается элемент в подмассиве, если есть,
     * Иначе возвращается первый элемент следующего подмассива
     * @exception NoSuchElementException
     * @return элемент.
     * */

    @Override
    public Object next() {
        Object result = null;

        int indexOfFirstNoEmptyArray = indexOfNoEmptySubArray();
        if (subArrayIndex >= multiArray.length
            || (indexOfValueInSubArray >= multiArray[subArrayIndex].length && indexOfFirstNoEmptyArray == -1)) {
                throw new NoSuchElementException();
        }

        if (indexOfValueInSubArray < multiArray[subArrayIndex].length) {
            result = multiArray[subArrayIndex][indexOfValueInSubArray++];
        } else {
            indexOfValueInSubArray = 0;
            result = multiArray[indexOfFirstNoEmptyArray][indexOfValueInSubArray++];
            subArrayIndex = indexOfFirstNoEmptyArray;
        }

        return result;
    }

}
