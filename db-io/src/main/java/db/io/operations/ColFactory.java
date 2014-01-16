package db.io.operations;

import com.google.common.collect.ImmutableMap;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.google.common.base.Throwables.*;
import static java.sql.Types.*;

class ColFactory {
    //
    // type mapping: http://db.apache.org/ojb/docu/guides/jdbc-types.html
    // TODO -
    private static ImmutableMap<Integer, Class<?>> COL_MAP =
            new ImmutableMap.Builder<Integer, Class<?>>()
                    //.put(ARRAY, Column< Arrays.>)
                    .put(CHAR, String.class)
                            //.put(ARRAY, Class.forName("[I"))
                            //.put(VARCHAR, new Column<>(String.class))
                    .put(VARCHAR, String.class)
                    .put(NVARCHAR, String.class)
                    .put(NUMERIC, BigDecimal.class)
                    .put(DECIMAL, BigDecimal.class)
                    .put(BIT, Boolean.class)
                    .put(BOOLEAN, Boolean.class)
                    .put(TINYINT, Byte.class)
                    .put(SMALLINT, Integer.class)
                    .put(INTEGER, Integer.class)
                    .put(BIGINT, Long.class)
                    .put(DATE, java.sql.Date.class)
                    .put(TIME, java.sql.Time.class)
                    .put(TIMESTAMP, java.sql.Timestamp.class)
                    .build();



    private static ImmutableMap<Class<?>, ColReader<?>> READER_MAP =
            new ImmutableMap.Builder<Class<?>, ColReader<?>>()
                    .put(String.class, new ColReader<String>() {

                        public String read(String colName, ResultSet rs) throws SQLException {
                            return rs.getString(colName);
                        }
                    })
                    .put(BigDecimal.class, new ColReader<BigDecimal>() {

                        public BigDecimal read(String colName, ResultSet rs) throws SQLException {

                            return rs.getBigDecimal(colName);
                        }
                    })
                    .put(Boolean.class, new ColReader<Boolean>() {

                        public Boolean read(String colName, ResultSet rs) throws SQLException {
                            return rs.getBoolean(colName);
                        }
                    })
                    .put(Byte.class, new ColReader<Byte>() {

                        public Byte read(String colName, ResultSet rs) throws SQLException {
                            return rs.getByte(colName);
                        }
                    })
                    .put(Integer.class, new ColReader<Integer>() {

                        public Integer read(String colName, ResultSet rs) throws SQLException {
                            return rs.getInt(colName);
                        }
                    })
                    .put(Long.class, new ColReader<Long>() {

                        public Long read(String colName, ResultSet rs) throws SQLException {
                            return rs.getLong(colName);
                        }
                    })
                    .put(java.sql.Date.class, new ColReader<java.sql.Date>() {

                       public Date read(String colName, ResultSet rs) throws SQLException {
                        return rs.getDate(colName);
                       }
                    })
                    .put(java.sql.Timestamp.class, new ColReader<Timestamp>() {
                        @Override public Timestamp read(String colName, ResultSet rs) throws SQLException {
                            return rs.getTimestamp(colName);
                        }
                    })
                    .build();


    static Column newCol(String name, int sqlType) {
        return new Column(COL_MAP.get(sqlType), name, null);
    }

    static <T> T newColVal(ResultSet rs, String name, Class<T> type) {
        try {
            return type.cast(READER_MAP.get(type).read(name, rs));
        } catch (SQLException e) {
            throw propagate(e);
        }
    }

    static ColReader<?> newColReader(Class<?> type) {
        return READER_MAP.get(type);
    }
}
