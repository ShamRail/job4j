package tree;

import java.util.Iterator;

public interface TreeIterator<T> extends Iterator<T> {
    boolean isAccordChildCount(int children);
}
