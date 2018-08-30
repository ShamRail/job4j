package map;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    Map<User, Object> users = new HashMap<>();

    @Before
    public void setUp() {
        User user1 = new User("Artem", 0, new GregorianCalendar(2000, Calendar.NOVEMBER, 1));
        User user2 = new User("Artem", 0, new GregorianCalendar(2000, Calendar.NOVEMBER, 1));
        users.put(user1, new Object());
        users.put(user2, new Object());
    }

    @Test
    public void whenEqualsAndHashcodeUndefinedThenBothUsersWillBeInMap() {
        System.out.println(users);
        Assert.assertThat(users.size(), Is.is(2));
    }

    @Test
    public void whenOnlyHashcodeDefinedThenBothUsersWillBeInMap() {
        System.out.println(users);
        Assert.assertThat(users.size(), Is.is(2));
    }
}