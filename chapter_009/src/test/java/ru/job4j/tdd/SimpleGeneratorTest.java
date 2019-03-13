package ru.job4j.tdd;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleGeneratorTest {

    @Test
    public void whenAlArgsCorrectAndOnePairThenMustReplaceValuesByKeys() {
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        Map<String, String> pairs = new HashMap<>();
        pairs.put("sos", "Aaaa");
        String template = "Help, ${sos}, ${sos}, ${sos}";
        String result = simpleGenerator.replace(template, pairs);
        Assert.assertThat(result, Is.is("Help, Aaaa, Aaaa, Aaaa"));
    }

    @Test
    public void test() {
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        Map<String, String> pairs = new HashMap<>();
        pairs.put("sos", "Aaaa");
        String template = "Help, ${sos}, ${sos}, ${sos}";
        String result = simpleGenerator.replace(template, pairs);

        Pattern pattern = Pattern.compile("\\$\\{so}");
        Matcher matcher = pattern.matcher(template);
        boolean res = matcher.find();
        System.out.println(res);

        Assert.assertThat(result, Is.is("Help, Aaaa, Aaaa, Aaaa"));
    }

    @Test
    public void whenAlArgsCorrectAndTwoPairThenMustReplaceValuesByKeys() {
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        Map<String, String> pairs = new HashMap<>();
        pairs.put("name", "Rail");
        pairs.put("sub", "you");
        String template = "I am a ${name}, Who are ${sub}?";
        String result = simpleGenerator.replace(template, pairs);
        Assert.assertThat(result, Is.is("I am a Rail, Who are you?"));
    }

    @Test(expected = InvalidStringTemplate.class)
    public void whenInputPairLessThanNeededThenMustBeException() {
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        Map<String, String> pairs = new HashMap<>();
        pairs.put("name", "Rail");
        String template = "I am a ${name}, Who are ${sub}?";
        String result = simpleGenerator.replace(template, pairs);
    }

    @Test(expected = InvalidStringTemplate.class)
    public void whenInputPairMoreThanNeededThenMustBeException() {
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        Map<String, String> pairs = new HashMap<>();
        pairs.put("name", "Rail");
        pairs.put("sub", "you");
        pairs.put("profession", "programmer");
        String template = "I am a ${name}, Who are ${sub}?";
        String result = simpleGenerator.replace(template, pairs);
    }
}