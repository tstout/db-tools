package db.io.config;

public class DBUrl implements Property<String> {
    private final String val;

    DBUrl(String val) {
        this.val = val;
    }

    //
    // TODO - is a builder justified here for the url components?
    //
    @Override public String value() {
        return val;
    }
}
