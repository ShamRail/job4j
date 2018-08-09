package ru.job4j.sort;


import java.util.*;

public class SortUser {
    /**
     * sort.
     * Сортирует  по возрасту.
     * @param users - пользователи.
     * @return отсортированный список.
     * */
    public Set<User> sort(List<User> users) {
        Set<User> result = new TreeSet<>(User::compareTo);
        result.addAll(users);
        return result;
    }
    /**
     * sortNameLenght.
     * Сортирует сначало по имени.
     * @param users - пользователи.
     * @return отсортированный список.
     * */
    public List<User> sortNameLength(List<User> users) {
        users.sort(Comparator.comparing(User::getName));
        return users;
    }
    /**
     * sortByAllFields.
     * Сортирует сначало по имени , потом по возрасту.
     * @param users - пользователи.
     * @return отсортированный список.
     * */
    public List<User> sortByAllFields(List<User> users) {
        users.sort(Comparator.comparing(User::getName).thenComparingInt(User::getAge));
        return users;
    }
}
