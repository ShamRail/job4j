package filefinder.search.factory;

import filefinder.search.searchtypes.SearchByMask;
import filefinder.search.searchtypes.SearchByName;
import filefinder.search.searchtypes.SearchType;

/**
 * Фабрика типов поиска.
 */
public class SearchTypeFactory implements TypeFactory {

    /**
     * Создает тип поиска по шаблону.
     * @param template шаблон.
     * @return тип поиска.
     */
    @Override
    public SearchType createType(String template) {
        SearchType type = null;
        if (template.equals("-m")) {
            type = new SearchByMask();
        }
        if (template.equals("-f")) {
            type = new SearchByName();
        }
        return type;
    }
}
