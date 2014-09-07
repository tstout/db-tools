package db.io.config;

import com.google.common.base.Objects;

import static com.google.common.base.Optional.*;

public class DBHost implements Property<String> {
    private final String val;

    public DBHost(String val) {
        this.val = val;
    }

    public DBHost() {
        this.val = null;
    }

    @Override public String value() {
        return fromNullable(val).or("");
    }

    @Override public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final DBHost other = (DBHost) obj;
        return Objects.equal(this.val, other.val);
    }
}
