package filefinder.search.searcher;

import java.io.File;
import java.util.*;

/**
 * Поисковик файлов.
 */
public class FileSearcher implements Searcher<List<File>, SearchParams> {

    /**
     * Ищет файлы, соответствующие параметрам поиска.
     * Поиск осуществляется в ширину.
     * Начальная директория и шаблон поиска задается в параметрах.
     * @param searchParams параметры поиска.
     * @return список, найденных файлов.
     */
    @Override
    public List<File> search(SearchParams searchParams) {
        List<File> rst = new LinkedList<>();
        File start = new File(searchParams.getStartDirectory());
        Queue<File> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            File current = queue.poll();
            if (current.isDirectory()) {
                File[] files = current.listFiles();
                if (files != null && files.length != 0) {
                    Arrays.stream(files).forEach(queue::offer);
                }
                continue;
            }
            if (searchParams.getSearchType().compare(current.getName(), searchParams.getSamples())) {
                rst.add(current);
            }
        }
        return rst;
    }
}
