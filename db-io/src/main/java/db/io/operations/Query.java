package db.io.operations;

public interface Query {
    DataSet execute(String sql, Object... args);

    //<T> Collection<T> run(Class<T> clazz);
}

