package arrays;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;


public class EqualWordsTest {

    @Test
    public void whenEqualStringsThenReturnTrue() {
        char[] word1 = "car".toCharArray();
        char[] word2 = "car".toCharArray();
        boolean expected = true;
        boolean result = new EqualWords().check(word1, word2);
        Assert.assertThat(result, Is.is(expected));
    }

    @Test
    public void whenOneSubstringThenReturnTrue() {
        char[] word1 = "big car".toCharArray();
        char[] word2 = "big".toCharArray();
        boolean expected = true;
        boolean result = new EqualWords().check(word1, word2);
        Assert.assertThat(result, Is.is(expected));
    }

    @Test
    public void whenEqualLengthAndCanReplaceThenReturnTrue() {
        char[] word1 = "Привет".toCharArray();
        char[] word2 = "Приевт".toCharArray();
        boolean expected = true;
        boolean result = new EqualWords().check(word1, word2);
        Assert.assertThat(result, Is.is(expected));
    }

    @Test
    public void whenCanReplaceThenReturnTrue() {
        char[] word1 = "Привет".toCharArray();
        char[] word2 = "2131При213евт".toCharArray();
        boolean expected = true;
        boolean result = new EqualWords().check(word1, word2);
        Assert.assertThat(result, Is.is(expected));
    }

    @Test
    public void whenCantReplaceThenReturnTrue() {
        char[] word1 = "Привет".toCharArray();
        char[] word2 = "Пока".toCharArray();
        boolean expected = false;
        boolean result = new EqualWords().check(word1, word2);
        Assert.assertThat(result, Is.is(expected));
    }

}