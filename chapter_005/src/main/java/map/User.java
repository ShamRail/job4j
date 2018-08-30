package map;

import java.util.Calendar;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
        result = prime * result + children;
        result = prime * result + birthday.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        boolean checked = false;
        if (this == obj) {
            result = true;
            checked = true;
        }

        if (!checked && (obj == null || this.getClass() != obj.getClass())) {
            checked = true;
        }

        if (!checked) {
            User user = (User) obj;
            result = (this.children == user.children)
                    && (this.birthday.getTimeInMillis() == user.birthday.getTimeInMillis())
                    && (this.name != null && user.name != null && this.name.equals(user.name));
        }
        return result;
    }
}
