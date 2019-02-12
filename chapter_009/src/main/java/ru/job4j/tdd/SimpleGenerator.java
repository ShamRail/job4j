package ru.job4j.tdd;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class SimpleGenerator {

    /**
     * Replace all matches key to values.
     * If template is invalid then throw exception.
     * Since string is immutable it's representation rewrites into temporary list.
     * @param template source template.
     * @param pairs sorsce pairs to replace.
     * @return changed string.
     */

    public String replace(String template, Map<String, String> pairs) {
        checkout(template, pairs);
        LinkedList<String> result = new LinkedList<>();
        result.add(template);
        for (Map.Entry<String, String> entry : pairs.entrySet()) {
            result.add(result.getLast().replace(String.format("${%s}", entry.getKey()), entry.getValue()));
        }
        return result.getLast();
    }

    /**
     * Check out input string template.
     * If it is incorrect throws exception.
     * At first, find all existed keys.
     * At second, compare it with source key.
     * @param template source string.
     * @param pairs source pairs.
     */

    private void checkout(String template, Map<String, String> pairs) {
        Set<String> keysIn = new HashSet<>();
        StringBuilder currentKey = new StringBuilder();
        boolean toConcatenate = false;
        char[] array = template.toCharArray();
        for (char c : array) {
            if (c == '{') {
                toConcatenate = true;
                continue;
            }
            if (c == '}') {
                toConcatenate = false;
                String key = currentKey.toString();
                keysIn.add(key);
                currentKey = new StringBuilder();
            }
            if (toConcatenate) {
                currentKey.append(c);
            }
        }
        if (keysIn.size() != pairs.size()) {
            throw new InvalidStringTemplate("Invalid template or pairs!");
        }
        for (Map.Entry<String, String> entry : pairs.entrySet()) {
            if (!keysIn.contains(entry.getKey())) {
                throw new InvalidStringTemplate("Invalid template or pairs!");
            }
        }
    }

}
