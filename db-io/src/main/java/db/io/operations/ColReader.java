package db.io.operations;

import java.sql.ResultSet;
import java.sql.SQLException;

interface ColReader<T> {
    T read(String colName, ResultSet rs) throws SQLException;
}
