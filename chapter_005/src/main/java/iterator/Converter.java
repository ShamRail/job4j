package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Converter.
 * Convert iterator of iterators to one iterator.
 * @since 25.08.2018
 * @version 1.0.
 * @author Rail Shamsemukhametov.
 * */

public class Converter {
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            /**Pointer to current iterator.*/
            private Iterator<Integer> pointer = this.movePointerToFirstNoEmptyInterator();
            /**Flag of existence next element.*/
            private boolean isNextElementExist = pointer != null && pointer.hasNext();

            public boolean hasNext() {
                return this.isNextElementExist;
            }

            public Integer next() {
                if (!isNextElementExist) {
                    throw new NoSuchElementException();
                }
                if (this.thisIteratorIsTraversed()) {
                    this.movePointerToFirstNoEmptyInterator();
                }
                Integer result = pointer.next();
                isNextElementExist = pointer.hasNext() || it.hasNext();
                return  result;
            }

            /**
             * thisIteratorIsTraversed.
             * Check pointer's value position.
             * In ending(true) or not(false).
             * @return result of checking.
             * */

            private boolean thisIteratorIsTraversed() {
                return !this.pointer.hasNext();
            }

            /**
             * movePointerToFirstNoEmptyInterator.
             * */

            private Iterator<Integer> movePointerToFirstNoEmptyInterator() {
                Iterator<Integer> result = null;
                while (it.hasNext()) {
                    pointer = it.next();
                    result = pointer;
                    if (pointer.hasNext()) {
                        break;
                    }
                }
                return result;
            }
        };
    }

}
