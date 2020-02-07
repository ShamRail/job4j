package dictionaries;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Email {

    private String name;

    private Set<User> users = new LinkedHashSet<>();

    public Email(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(User user) {
        users.add(user);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Email email = (Email) o;
        return Objects.equals(name, email.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Email{"
                + "name='"
                + name
                + '\''
                + '}';
    }
}
