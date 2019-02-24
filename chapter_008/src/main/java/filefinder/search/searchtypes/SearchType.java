package filefinder.search.searchtypes;

import java.util.Collection;

/**
 * Описывает тип поиска.
 */
public interface SearchType {

    /**
     * Метод сравнения имени файла с образцами.
     * @param source сравниваемый файл.
     * @param samples образцы.
     * @return true если исходный файл соответсвует одному из шаблонов, иначе false.
     */
    boolean compare(String source, Collection<String> samples);

}
