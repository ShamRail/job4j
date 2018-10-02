package nonblocking;

import java.util.Objects;

public class Base {

    private final int id;
    private int version;
    private String data;

    public Base(int id, int version, String data) {
        this.id = id;
        this.version = version;
        this.data = data;
    }

    public int getVersion() {
        return version;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id && version == base.version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }

    public void increaseVersion() {
        this.version++;
    }

    public String getData() {
        return data;
    }
}
