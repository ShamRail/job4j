package filefinder.search.searchtypes;

import java.util.Collection;
import java.util.function.BiPredicate;
/**
 * Поиск по имени файла.
 */
public class SearchByName implements BiPredicate<String, Collection<String>> {

    /**
     * Сопоставляет по имени.
     * @param source сравниваемый файл.
     * @param samples образцы.
     * @return результат сравнения.
     */
    @Override
    public boolean test(String source, Collection<String> samples) {
        return samples.stream().anyMatch((sample) -> sample.equals(source));
    }

}
