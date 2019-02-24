package filefinder.search.searchtypes;

import java.util.Collection;

/**
 * Поиск по маске файла.
 */
public class SearchByMask implements SearchType {

    /**
     * Сопоставляет исходный файл по маске.
     * @param source сравниваемый файл.
     * @param samples образцы.
     * @return результат сравнения.
     */
    @Override
    public boolean compare(String source, Collection<String> samples) {
        int delimiterIndex = source.lastIndexOf('.');
        boolean result = delimiterIndex != -1;
        return result
                && samples.stream()
                   .anyMatch((sample) -> sample.equals(String.format("*%s", source.substring(delimiterIndex))));
    }
}
