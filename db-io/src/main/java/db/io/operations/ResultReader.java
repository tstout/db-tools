package db.io.operations;

import java.sql.ResultSet;

public interface ResultReader {
    DataSet read(ResultSet rs);
}
