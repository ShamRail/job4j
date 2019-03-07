package filefinder.search.searcher;

import java.util.Collection;
import java.util.function.BiPredicate;

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
    private BiPredicate<String, Collection<String>> searchType;

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

    public BiPredicate<String, Collection<String>> getSearchType() {
        return searchType;
    }

    public void setSearchType(BiPredicate<String, Collection<String>> searchType) {
        this.searchType = searchType;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getLogPath() {
        return this.logPath;
    }
}
