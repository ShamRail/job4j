package ru.job4j.list;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UserConvertTest {
    @Test
    public void whenConvertListToHashMap() {
        User user1 = new User(1, "Rail", "Ufa");
        User user2 = new User(2, "Vadim", "Moscow");
        User user3 = new User(3, "Maksim", "Vladivostock");
        List<User> list = Arrays.asList(user1, user2, user3);
        HashMap<Integer, User> result = new UserConvert().process(list);
        Assert.assertThat(result.get(3), Is.is(user3));
    }
}
