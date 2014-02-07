package db.io.operations;

import java.sql.ResultSet;

interface ResultReader {
    DataSet read(ResultSet rs);
}
