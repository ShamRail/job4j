package dictionaries;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

public class EmailsTest {


    @Test
    public void test() {

        Map<String, Set<String>> input = new LinkedHashMap<>();

        input.put("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        input.put("user2", Set.of("foo@gmail.com", "ups@pisem.net"));
        input.put("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"));
        input.put("user4", Set.of("ups@pisem.net", "aaa@bbb.ru"));
        input.put("user5", Set.of("xyz@pisem.net"));

        Map<String, Set<String>> expected = Map.of(
                "user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru"),
                "user3", Set.of("xyz@pisem.net", "vasya@pupkin.com")
        );

        Map<String, Set<String>> output = Emails.handle(input);
        Assert.assertThat(output, Is.is(expected));
    }

    @Test
    public void test2() {

        Map<String, Set<String>> input = new LinkedHashMap<>();

        input.put("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        input.put("user2", Set.of("foo@gmail.com", "ups@pisem.net"));
        input.put("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"));
        input.put("user4", Set.of("ups@pisem.net", "aaa@bbb.ru"));
        input.put("user5", Set.of("xyz@pisem.net"));

        Map<String, Set<String>> expected = Map.of(
                "user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru"),
                "user3", Set.of("xyz@pisem.net", "vasya@pupkin.com")
        );

        Map<String, Set<String>> output = Emails.handle2(input);
        Assert.assertThat(output, Is.is(expected));
    }

    @Test
    public void test3() {

        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");
        User user4 = new User("user4");
        User user5 = new User("user5");

        Email email1 = new Email("xxx@ya.ru");
        Email email2 = new Email("foo@gmail.com");
        Email email3 = new Email("lol@mail.ru");
        Email email4 = new Email("ups@pisem.net");
        Email email5 = new Email("aaa@bbb.ru");
        Email email6 = new Email("xyz@pisem.net");
        Email email7 = new Email("vasya@pupkin.com");

        user1.setEmails(new LinkedHashSet<>(Arrays.asList(email1, email2, email3)));
        user2.setEmails(new LinkedHashSet<>(Arrays.asList(email2, email4)));
        user3.setEmails(new LinkedHashSet<>(Arrays.asList(email6, email7)));
        user4.setEmails(new LinkedHashSet<>(Arrays.asList(email4, email5)));
        user5.setEmails(new LinkedHashSet<>(Arrays.asList(email6)));

        email1.setUsers(new LinkedHashSet<>(Arrays.asList(user1)));
        email2.setUsers(new LinkedHashSet<>(Arrays.asList(user1, user2)));
        email3.setUsers(new LinkedHashSet<>(Arrays.asList(user1)));
        email4.setUsers(new LinkedHashSet<>(Arrays.asList(user2, user4)));
        email5.setUsers(new LinkedHashSet<>(Arrays.asList(user4)));
        email6.setUsers(new LinkedHashSet<>(Arrays.asList(user3)));
        email7.setUsers(new LinkedHashSet<>(Arrays.asList(user3, user5)));


        User euser1 = new User("user1");
        User euser2 = new User("user3");

        euser1.setEmails(new LinkedHashSet<>(Arrays.asList(email1, email2, email3, email4, email5)));
        euser2.setEmails(new LinkedHashSet<>(Arrays.asList(email6, email7)));

        Set<User> expected = new LinkedHashSet<>(Arrays.asList(euser1, euser2));
        Set<User> output = Emails.handle3(new LinkedHashSet<>(Arrays.asList(user1, user2, user3, user4, user5)));

        Assert.assertThat(output, Is.is(expected));
    }




}