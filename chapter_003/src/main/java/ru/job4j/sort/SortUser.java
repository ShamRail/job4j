package ru.job4j.sort;


import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {
    public Set<User> sort(List<User> users) {
        Set<User> result = new TreeSet<User>();
        for (int i = 1; i < users.size(); i++) {
            int resultOfCompare = users.get(i - 1).compareTo(users.get(i));
            if (resultOfCompare >= 0) {
                result.add(users.get(i));
                result.add(users.get(i - 1));
            } else{
                result.add(users.get(i - 1));
                result.add(users.get(i));
                }
            }
        return result;
    }

}
