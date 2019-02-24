package filefinder.search.factory;

import filefinder.search.searchtypes.SearchType;

/**
 * Описывает способ создания типа поиска по шаблону.
 */
public interface TypeFactory {

    /**
     * Создает тип поиска по указанному шаблону.
     * @param template шаблон.
     * @return тип поиска.
     */
    SearchType createType(String template);

}
