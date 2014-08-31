package db.io.config;

import com.google.common.base.Objects;

import static com.google.common.base.Optional.fromNullable;

public class DBDir implements Property<String> {
    private final String dbDir;

    public DBDir(String dbDir) {
        this.dbDir = dbDir;
    }

    public DBDir() { this.dbDir = null; }

    @Override public String value() {
        return fromNullable(dbDir).or("");
    }

    @Override public int hashCode() {
        return Objects.hashCode(dbDir);
    }

    @Override public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final DBDir other = (DBDir) obj;
        return Objects.equal(this.dbDir, other.dbDir);
    }
}
