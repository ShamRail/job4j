package arrays;

import java.util.*;

public class EqualWords {

    /**
     * Выбираем слово - то, что длиннее. Пытаемся в нем
     * составить слово покроче за счет перестановок, а лишние буквы откинуть.
     * Т.к. удалеление делается долго, то нам достаточно проверить, что
     * слово покороче является подстрокой слова подлинее. Если это удается сделать,
     * значит слова были изначально приближенно равны. Чтобы понизить сложность
     * алгоритма будем использовать словарь, где хранятся символ и список индексов его
     * входений. Если мы находим нужный символ делаем замену в слове, а индекс удаляем.
     * */

    public boolean check(char[] word1, char[] word2) {
        char[] first;
        char[] second;
        if (word1.length > word2.length) {
            first = Arrays.copyOf(word1, word1.length);
            second = word2;
        } else {
            first = Arrays.copyOf(word2, word2.length);
            second = word1;
        }
        Map<Character, LinkedList<Integer>> map = getMap(first);
        tryBuild(first, second, map);
        return new String(first).contains(new String(second));
    }

    private Map<Character, LinkedList<Integer>> getMap(char[] word) {
        Map<Character, LinkedList<Integer>> map = new HashMap<>();
        for (int i = 0; i < word.length; i++) {
            if (map.containsKey(word[i])) {
                map.get(word[i]).addLast(i);
            } else {
                LinkedList<Integer> indexes = new LinkedList<>();
                indexes.addLast(i);
                map.put(word[i], indexes);
            }
        }
        return map;
    }

    private void tryBuild(char[] first, char[] second, Map<Character, LinkedList<Integer>> map) {
        for (int i = 0; i < second.length; i++) {
            if (first[i] != second[i]) {
                if (map.containsKey(second[i]) && !map.get(second[i]).isEmpty()) {
                    int index = map.get(second[i]).pollFirst();
                    char temp = first[i];
                    first[i] = first[index];
                    first[index] = temp;
                }
            } else {
                map.get(first[i]).pollFirst();
            }
        }
    }

}
