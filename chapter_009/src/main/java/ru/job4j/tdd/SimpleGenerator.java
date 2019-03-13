package ru.job4j.tdd;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String result = template;
        for (Map.Entry<String, String> set : pairs.entrySet()) {
            result = result.replaceAll(String.format("\\$\\{%s\\}", set.getKey()), set.getValue());
        }
        return result;
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
        boolean rst = true;
        for (Map.Entry<String, String> set : pairs.entrySet()) {
            Pattern pattern = Pattern.compile(String.format("\\$\\{%s}", set.getKey()));
            Matcher matcher = pattern.matcher(template);
            if (!matcher.find()) {
                rst = false;
                break;
            }
        }
        Set<String> allMatches = getMatches("\\$\\{\\w*}", template);
        if (!rst || pairs.size() > allMatches.size() || !allMatches.equals(pairs.keySet())) {
            throw new InvalidStringTemplate("Invalid template or pairs!");
        }
    }

    private Set<String> getMatches(String regex, String template) {
        Matcher m = Pattern.compile(regex).matcher(template);
        Set<String> allMatches = new HashSet<>();
        while (m.find()) {
            String found = m.group();
            allMatches.add(found.substring(found.indexOf('{') + 1, found.lastIndexOf('}')));
        }
        return allMatches;
    }
}
