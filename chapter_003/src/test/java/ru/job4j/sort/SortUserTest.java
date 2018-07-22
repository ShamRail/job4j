package ru.job4j.sort;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUserTest {
    @Test
    public void whenSortUsersWithoutSameNames() {
        User user1 = new User("Andrei", 19);
        User user2 = new User("Boris", 18);
        List<User> users = Arrays.asList(user1, user2);
        Set<User> result = new SortUser().sort(users);
        Assert.assertThat(((TreeSet<User>) result).last(), Is.is(user2));
    }
    @Test
    public void whenSortUsersWithOutSameNames() {
        User user1 = new User("Boris", 17);
        User user2 = new User("Andrei", 18);
        List<User> users = Arrays.asList(user1, user2);
        Set<User> result = new SortUser().sort(users);
        Assert.assertThat(((TreeSet<User>) result).last(), Is.is(user1));
    }

    @Test
    public void whenSortUsersWithSameNames() {
        User user1 = new User("Andrei", 18);
        User user2 = new User("Andrei", 17);
        List<User> users = Arrays.asList(user1, user2);
        Set<User> result = new SortUser().sort(users);
        Assert.assertThat(((TreeSet<User>) result).last(), Is.is(user1));
    }
}
