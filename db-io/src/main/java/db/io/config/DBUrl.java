package db.io.config;


import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

public class DBUrl implements Property<String> {
    private final String val;

    public DBUrl(String val) {
        this.val = checkNotNull(val);
    }

    @Override public String value() {
        return val;
    }

    @Override public int hashCode() {
        return Objects.hashCode(val);
    }

    @Override public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final DBUrl other = (DBUrl) obj;
        return Objects.equal(this.val, other.val);
    }
}
