package db.io.config;

public class DBUrl implements Property<String> {
    private final String val;

    DBUrl(String val) {
        this.val = val;
    }

    @Override public String value() {
        return val;
    }
}