package filefinder.validate;

import filefinder.search.searcher.SearchParams;
import filefinder.search.factory.SearchTypeFactory;
import filefinder.search.factory.TypeFactory;
import filefinder.tip.Tip;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Описывает способ валидации строки.
 * При возврате параметров поиска.
 */
public class StringValidator implements Validator<SearchParams, String[]> {

    /**
     * Подсказка, которая выводится если строка не прощла валидацию.
     */
    private Tip tip;

    public StringValidator(Tip tip) {
        this.tip = tip;
    }

    /**
     * Метод проверки строки, разбитую на подстроки.
     * Происходит проверка на наличие всех необходимых ключей,
     * на существование директории поиска и возможность создания лога.
     * Если валидация проходит успешно, то возвращается объект поиска,
     * заполненный исходными данными, иначе null.
     * @param input источник.
     * @return объект поиска.
     */
    @Override
    public SearchParams validate(String[] input) {
        SearchParams searchParams = null;
        boolean unChecked = true;
        if (!checkoutKeys(input)) {
            tip.showMessage("Недостаточно ключей. Должны быть -d -n -m (|| -f || -r) -o");
            unChecked = false;
        }
        if (unChecked && !checkoutDirectory(input[1])) {
            tip.showMessage("Неверно задана директория поиска.");
            unChecked = false;
        }
        unChecked = unChecked && createLogFile(input[6]);
        if (unChecked) {
            searchParams = new SearchParams();
            fillParams(searchParams, input);
        }
        return searchParams;
    }

    /**
     * Проверяет достаточно ли ключей передано.
     * @param input исходная строка.
     * @return результат.
     */
    private boolean checkoutKeys(String[] input) {
        return input[0].equals("-d") && input[2].equals("-n")
                && (input[4].equals("-m") || input[4].equals("-f") || input[4].equals("-r"))
                && input[5].equals("-o");
    }

    /**
     * Проверяет существует ли директория поиска.
     * @param directory директория.
     * @return результат.
     */
    private boolean checkoutDirectory(String directory) {
        File dir = new File(directory);
        return dir.isDirectory() && dir.exists();
    }

    /**
     * Создает лог файл.
     * Если появляется ошибка, то пользователь об этом узнает.
     * @param name имя создаваемого файла.
     * @return результат.
     */
    private boolean createLogFile(String name) {
        File file = new File(name);
        boolean result = false;
        try {
            file.createNewFile();
            result = true;
        } catch (IOException e) {
            tip.showMessage("Ошибка при создании лог файла. Удостовертесь, что указали верное имя или расположение файла");
        }
        return result;
    }

    /**
     * Заполняет параметры поиска исходными данными.
     * Доступна передача нескольких образцов (имени или маски).
     * Только если они перечислены через запятую и без пробела.
     * Метод не может возвращать ошибок, т.к. до него уже пройдена валидация.
     * @param params куда записывается.
     * @param input откуда записывается.
     */
    private void fillParams(SearchParams params, String[] input) {
        TypeFactory typeFactory = new SearchTypeFactory();
        params.setStartDirectory(input[1]);
        params.setSamples(new HashSet<>(Arrays.asList(input[3].split(",(?!\\s)"))));
        params.setSearchType(typeFactory.createType(input[4]));
        params.setLogPath(input[6]);
    }
}
