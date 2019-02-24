package filefinder;

import filefinder.log.*;
import filefinder.search.searcher.FileSearcher;
import filefinder.search.searcher.SearchParams;
import filefinder.search.searcher.Searcher;
import filefinder.tip.*;
import filefinder.validate.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Точка запуска приложения.
 */
public class StartUI {

    /**
     * Приложение запускается внешене через main.
     * Во-первых, происзодит валидация.
     * Во-вторых, поиск.
     * В-третьих, запись в лог.
     * Два последних шага проходят, если будет пройдена валидация.
     * @param args - переданные пользователем аргументы поиска.
     */
    public static void main(String[] args) throws IOException {
        Tip tip = new ConsoleTip();
        Validator<SearchParams, String[]> validator = new StringValidator(tip);
        SearchParams searchParams = validator.validate(args);
        if (searchParams != null) {
            Searcher<List<File>, SearchParams> searcher = new FileSearcher();
            List<File> foundFile =  searcher.search(searchParams);
            LogWriter<String, List<File>> logWriter = new FileLogger();
            logWriter.write(searchParams.getLogPath(), foundFile);
        }
    }

}
