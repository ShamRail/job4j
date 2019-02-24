package filefinder.log;

import java.io.IOException;

/**
 * Описывает способ логирования.
 * @param <T> куда записываем.
 * @param <R> откуда пишем.
 */
public interface LogWriter<T, R> {

    /**
     * Записывает лог.
     * @param destination назначение записи.
     * @param source источник записи.
     */
    void write(T destination, R source) throws IOException;

}
