package filefinder.search.searcher;

/**
 * Описывает способ поиска.
 * @param <T> результат поиска.
 * @param <R> параметры поиска.
 */
public interface Searcher<T, R> {

    /**
     * Метод поиска.
     * @param searchParams параметры поиска.
     * @return результат поиска.
     */
    T search(R searchParams);

}
