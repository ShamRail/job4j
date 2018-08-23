package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator {

    private int[] array;
    /**Индекс первого четного элемента.*/
    private int firstEvenIndex = -1;

    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    /**
     * firstEvenElement.
     * Находит индекс первого четного элемента.
     * @return индекс.
     * */

    private int firstEvenElement() {
        int result = -1;
        for (int i = firstEvenIndex + 1; i < this.array.length; i++) {
            if (this.array[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * hasNext.
     * Проверяет существование следующего четного элемента.
     * Проверяет не равен ли индекс -1.
     * Если равен значит элемента нет в массиве.
     * @return результат.
     * */

    @Override
    public boolean hasNext() {
        return firstEvenElement() != -1;
    }

    /**
     * next.
     * Возвращает четный элемент прежде находя его индекс.
     * Если индекс существует сдвигает указатель.
     * @return элемент.
     * */

    @Override
    public Object next() {
        int indexOfFirstEven = this.firstEvenElement();
        if (indexOfFirstEven == -1) {
            throw new NoSuchElementException();
        }
        firstEvenIndex = indexOfFirstEven;
        return this.array[firstEvenIndex];
    }
}
