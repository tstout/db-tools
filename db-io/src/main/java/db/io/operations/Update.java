package db.io.operations;

public interface Update {
    int update(String sql, Object... args);
}
