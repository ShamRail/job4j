package filefinder.validate;

/**
 * Описывает способ проверки.
 * @param <T> результат проверки.
 * @param <R> источкник проверки.
 */
public interface Validator<T, R> {

    /**
     * Проверяет переданный источник.
     * @param input источник.
     * @return результат проверки.
     */
    T validate(R input);

}
