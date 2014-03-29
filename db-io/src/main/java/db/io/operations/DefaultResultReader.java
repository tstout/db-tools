package db.io.operations;

import com.google.common.collect.ImmutableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.*;
import static com.google.common.base.Throwables.*;
import static com.google.common.collect.Lists.*;

class DefaultResultReader implements ResultReader {
    private final ImmutableList<ReaderPair> readers;

    static class ReaderPair {
        Class<?> type;
        String name;
        ColReader<?> reader;

        ReaderPair(Class<?> type, String name, ColReader<?> reader) {
            this.reader = checkNotNull(reader, "reader should not be null");
            this.type = type;
            this.name = name;
        }

        Object read(ResultSet rs) {
            try {
                return reader.read(name, rs);
            } catch (SQLException e) {
                throw propagate(e);
            }
        }

        Column newCol(ResultSet rs) {
            return new Column(type, name, read(rs));
        }
    }

    DefaultResultReader(Collection<Column> colMeta) {
        List<ReaderPair> readerList = newArrayList();

        for (Column col : colMeta) {
            readerList.add(new ReaderPair(col.type(), col.name(), ColFactory.newColReader(col.type())));
        }

        readers = ImmutableList.copyOf(readerList);
    }

    @Override public DataSet read(ResultSet rs) {
        DataSet ds = new DataSet();

        try {
            for (int row = 0; rs.next(); row++) {
                for (ReaderPair reader : readers) {
                    ds.put(row, reader.newCol(rs));
                }
            }
        } catch (SQLException e) {
            throw propagate(e);
        }

        return ds;
    }
}