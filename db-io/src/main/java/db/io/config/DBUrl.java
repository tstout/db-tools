package db.io.config;

import java.util.Objects;

public class DBUrl implements Property<String> {
    private final String val;

    public DBUrl(String val) {
        this.val = val;
    }

    @Override public String value() {
        return val;
    }

    @Override public int hashCode() {
        return Objects.hash(val);
    }

    @Override public boolean equals(Object obj) {
        return obj != null &&
                getClass() == obj.getClass() &&
                Objects.equals(this, obj);
    }
}
