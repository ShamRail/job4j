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
        Set<User> result = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.compareTo(o2);
            }
        });
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
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return users;
    }
    /**
     * sortByAllFields.
     * Сортирует сначало по имени , потом по возрасту.
     * @param users - пользователи.
     * @return отсортированный список.
     * */
    public List<User> sortByAllFields(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                final int result = o1.getName().compareTo(o2.getName());
                return result != 0 ? result : Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return users;
    }
}
