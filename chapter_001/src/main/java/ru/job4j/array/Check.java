package ru.job4j.array;

/**
 * Check.
 * @version 1.0.
 * @since 06.07.2018.
 * @author Rail Shamsemukhametov.
 * */

public class Check {

    /**
     * mono
     * Метод, проверяющий все ли элементы true
     * если нет выводит false
     * @param data - массив булевых значений
     * @return результат
     * */

    public boolean mono(boolean[] data) {
        boolean result = true;
        boolean first = data[0];
        for (int i = 1; i < data.length; i++) {
                if(first != data[i]) {
                    result = false;
                    break;
                }
            }
        return result;
    }
}
