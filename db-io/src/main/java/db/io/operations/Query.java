package db.io.operations;

import java.util.Collection;

public interface Query {
    <T> Collection<T> execute(Class<T> intf, String sql, Object... args);
}

