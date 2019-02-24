package filefinder.search.searcher;

import filefinder.search.searchtypes.SearchType;
import java.util.Collection;

/**
 * Параметры поиска.
 * Используется как аргумент при поиске.
 * Данный класс описывает ввод пользователя одним объектом.
 */
public class SearchParams {

    /**
     * Начальная директория.
     */
    private String startDirectory;

    /**
     * Шаблоны.
     */
    private Collection<String> samples;

    /**
     * Тип поиска.
     */
    private SearchType searchType;

    /**
     * Адрес логирования.
     */
    private String logPath;

    public String getStartDirectory() {
        return startDirectory;
    }

    public void setStartDirectory(String startDirectory) {
        this.startDirectory = startDirectory;
    }

    public Collection<String> getSamples() {
        return samples;
    }

    public void setSamples(Collection<String> samples) {
        this.samples = samples;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getLogPath() {
        return this.logPath;
    }
}
