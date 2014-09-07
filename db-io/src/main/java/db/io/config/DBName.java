package db.io.config;

import com.google.common.base.Objects;

import static com.google.common.base.Optional.*;

public class DBName implements Property<String> {
    private final String dbName;

    public DBName(String dbName) {
        this.dbName = dbName;
    }

    public DBName() { this.dbName = null; }

    @Override public String value() {
        return fromNullable(dbName).or("db");
    }

    @Override public int hashCode() {
        return Objects.hashCode(dbName);
    }

    @Override public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final DBName other = (DBName) obj;
        return Objects.equal(this.dbName, other.dbName);
    }
}
