package db.io.config;

// anemic for the moment...
public class DBUser implements Property<String> {
    private final String val;

    public DBUser(String val) {
        this.val = val;
    }

    @Override public String value() {
        return val;
    }
}
