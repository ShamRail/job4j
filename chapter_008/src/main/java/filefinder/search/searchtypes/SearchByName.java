package filefinder.search.searchtypes;

import java.util.Collection;

/**
 * Поиск по имени файла.
 */
public class SearchByName implements SearchType {

    /**
     * Сопоставляет по имени.
     * @param source сравниваемый файл.
     * @param samples образцы.
     * @return результат сравнения.
     */
    @Override
    public boolean compare(String source, Collection<String> samples) {
        return samples.stream().anyMatch((sample) -> sample.equals(source));
    }

}
