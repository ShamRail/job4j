package arrays;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class PermArrayTest {

    @Test
    public void whenWithPermutationThenTrue() {
        char[] word1 = "кот".toCharArray();
        char[] word2 = "отк".toCharArray();
        boolean result = new PermArray().check(word1, word2);
        boolean expected = true;
        Assert.assertThat(result, Is.is(expected));
    }

    @Test
    public void whenWithoutPermutationThenTrue() {
        char[] word1 = "кот1".toCharArray();
        char[] word2 = "отк".toCharArray();
        boolean result = new PermArray().check(word1, word2);
        boolean expected = false;
        Assert.assertThat(result, Is.is(expected));
    }

    @Test
    public void whenWithPermutationAndRepeatsThenTrue() {
        char[] word1 = "aab".toCharArray();
        char[] word2 = "aba".toCharArray();
        boolean result = new PermArray().check(word1, word2);
        boolean expected = true;
        Assert.assertThat(result, Is.is(expected));
    }

}