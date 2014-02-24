package db.io.operations;

import com.google.common.collect.ImmutableList;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

import static com.google.common.base.Throwables.*;
import static com.google.common.collect.Lists.*;

class QueryMeta {
    private final ImmutableList<Column> columns;

    QueryMeta(ResultSet rs) {
        try {
            ResultSetMetaData meta = rs.getMetaData();
            List<Column> cols = newArrayList();

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                cols.add(ColFactory.newCol(meta.getColumnName(i), meta.getColumnType(i)));
            }
            columns = ImmutableList.copyOf(cols);
        } catch (Exception e) {
            throw propagate(e);
        }
    }

    ResultReader newReader() {
        return new DefaultResultReader(columns);
    }
}
