package ru.job4j.array;

/**
 * ArrayChar.
 * @version 1.0.
 * @since 06.07.2018.
 * @author Rail Shamsemukhametov.
 * */

public class ArrayChar {
    /** Слово */
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * starWith
     * Проверяет, что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинается с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        for (int i = 0 ; i < value.length; i++){
            if(data[i] != value[i]){
                result = false;
                break;
            }
        }
        return result;
    }
}