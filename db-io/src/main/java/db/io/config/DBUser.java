package db.io.config;

import java.util.Objects;

import static com.google.common.base.Optional.*;

public class DBUser implements Property<String> {
    private final String val;

    public DBUser() { val = null; }

    public DBUser(String val) {
        this.val = val;
    }

    @Override public String value() {
        return fromNullable(val).or("sa");
    }

    @Override public int hashCode() {
        return Objects.hashCode(val);
    }

    @Override public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final DBUser other = (DBUser) obj;
        return com.google.common.base.Objects.equal(this.val, other.val);
    }
}
