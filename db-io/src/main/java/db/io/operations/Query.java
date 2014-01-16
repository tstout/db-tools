package db.io.operations;

import db.io.SqlStmt;

public interface Query {
    DataSet execute(SqlStmt stmt, String sql, Object... args);
}

