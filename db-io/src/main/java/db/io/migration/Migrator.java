package db.io.migration;

public interface Migrator {
    void update(String script);
    void update(Class<?> root, String script);
}
