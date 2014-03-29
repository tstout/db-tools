package db.io.config;

import com.google.common.base.Objects;

// Anemic for the moment...
public class DBPwd implements Property<String> {
    public static final DBPwd DEFAULT = new DBPwd("");

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
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        final DBPwd other = (DBPwd) obj;
        return Objects.equal(this.val, other.val);
    }
}
