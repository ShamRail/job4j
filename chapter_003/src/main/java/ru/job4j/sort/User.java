package ru.job4j.sort;

public class User implements Comparable<User> {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    /**
     * compareTo.
     * сравнение идет сначало по имени, потом по значению.
     * @param o - сравниваемый объект.
     * @return результат.
     * */
    @Override
    public int compareTo(User o) {
        final int result = this.name.compareTo(o.name);
        return result != 0 ? result : Integer.compare(this.age, o.age);
    }
}
