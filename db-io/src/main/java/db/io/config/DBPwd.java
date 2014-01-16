package db.io.config;

import java.util.Objects;

// Anemic for the moment...
public class DBPwd implements Property<String> {
    private String val;

    public DBPwd(String val) {
        this.val = val;
    }

    @Override public String value() {
        return val;
    }

    @Override public int hashCode() {
        return Objects.hashCode(val);
    }

    @Override public boolean equals(Object obj) {
        return Objects.equals(this, obj);
    }
}
