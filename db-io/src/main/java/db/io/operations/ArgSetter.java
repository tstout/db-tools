package db.io.operations;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import static com.google.common.base.Throwables.*;
import static com.google.common.collect.FluentIterable.*;
import static com.google.common.collect.Maps.*;

class ArgSetter {
    private final Map<Class<?>, Setters> setterMap = newHashMap();

    private final ImmutableSet<Class<?>> intfTypes = new ImmutableSet.Builder<Class<?>>()
            .add(Blob.class)
            .build();

    ArgSetter() {
        for (Setters setter : Setters.values()) {
            setterMap.put(setter.type(), setter);
        }
    }

    void setValues(PreparedStatement stmt, Object[] args) {
        int index = 1;

        for (Object arg : args) {
            try {
                Class klazz = from(intfTypes)
                        .firstMatch(Fn.isSqlIntf(arg.getClass()))
                        .or(arg.getClass());

                setterMap.get(klazz).set(index, stmt, arg);
                index++;
            } catch (SQLException e) {
                throw propagate(e);
            }
        }
    }

    static class Fn {
        private Fn() {
            throw new UnsupportedOperationException();
        }

        static Predicate<Class<?>> isSqlIntf(final Class<?> argClass) {
            return new Predicate<Class<?>>() {
                @Override public boolean apply(Class<?> input) {
                    return input.isAssignableFrom(argClass);
                }
            };
        }
    }

    enum Setters {
        TIMESTAMP {
            @Override void set(int index, PreparedStatement stmt, Object value) throws SQLException {
                stmt.setTimestamp(index, (Timestamp) value);
            }

            @Override Class<?> type() {
                return Timestamp.class;
            }
        },
        BLOB {
            @Override void set(int index, PreparedStatement stmt, Object value) throws SQLException {
                stmt.setBlob(index, (Blob) value);
            }

            @Override Class<?> type() {
                return Blob.class;
            }
        },
        BYTES {
            @Override void set(int index, PreparedStatement stmt, Object value) throws SQLException {
                stmt.setBytes(index, (byte[]) value);
            }

            @Override Class<?> type() {
                return byte[].class;
            }
        },
        INT {
            @Override
            void set(int index, PreparedStatement stmt, Object value) throws SQLException {
                stmt.setInt(index, (Integer) value);
            }

            @Override Class<?> type() {
                return Integer.class;
            }
        },
        STRING {
            @Override
            void set(int index, PreparedStatement stmt, Object value) throws SQLException {
                stmt.setString(index, (String) value);
            }

            @Override Class<?> type() {
                return String.class;
            }
        },
        BOOL {
            @Override void set(int index, PreparedStatement stmt, Object value) throws SQLException {
                stmt.setBoolean(index, (Boolean) value);
            }

            @Override Class<?> type() {
                return Boolean.class;
            }
        },
        BIGDECIMAL {
            @Override void set(int index, PreparedStatement stmt, Object value) throws SQLException {
                stmt.setBigDecimal(index, (BigDecimal) value);
            }

            @Override Class<?> type() {
                return BigDecimal.class;
            }
        },
        DATE {
            @Override void set(int index, PreparedStatement stmt, Object value) throws SQLException {
                stmt.setDate(index, (Date) value);
            }

            @Override Class<?> type() {
                return Date.class;
            }
        };

        abstract void set(int index, PreparedStatement stmt, Object value) throws SQLException;
        abstract Class<?> type();
    }
}
