package arrays;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class UniqueArrayTest {

    @Test
    public void whenUniqueThenReturnTrue() {
        char[] data = "123".toCharArray();
        boolean expected = true;
        boolean result = new UniqueArray().check(data);
        Assert.assertThat(result, Is.is(expected));
    }

    @Test
    public void whenNotUniqueThenReturnFalse() {
        char[] data = "1231".toCharArray();
        boolean expected = false;
        boolean result = new UniqueArray().check(data);
        Assert.assertThat(result, Is.is(expected));
    }

}