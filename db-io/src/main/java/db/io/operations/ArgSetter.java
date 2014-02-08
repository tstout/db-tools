package db.io.operations;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import static com.google.common.base.Throwables.*;
import static com.google.common.collect.Maps.*;

class ArgSetter {
    private final Map<Class<?>, Setters> setterMap = newHashMap();

    ArgSetter() {
        for (Setters setter : Setters.values()) {
            setterMap.put(setter.typeHandled(), setter);
        }
    }

    void setValues(PreparedStatement stmt, Object[] args) {
        int index = 0;

        for (Object arg : args) {
            try {
                setterMap.get(arg.getClass()).set(index, stmt, args[index++]);
            } catch (SQLException e) {
                throw propagate(e);
            }
        }
    }

    // TODO - need to add support for many more types
    enum Setters {
        INT {
            @Override
            void set(int index, PreparedStatement stmt, Object value) throws SQLException {
                stmt.setInt(0, (Integer) value);
            }

            @Override Class<?> typeHandled() {
                return Integer.class;
            }
        },
        STRING {
            @Override
            void set(int index, PreparedStatement stmt, Object value) throws SQLException {
                stmt.setString(index, (String) value);
            }

            @Override Class<?> typeHandled() {
                return String.class;
            }

        },
        BOOL {
            @Override void set(int index, PreparedStatement stmt, Object value) throws SQLException {
                stmt.setBoolean(index, (Boolean) value);
            }

            @Override Class<?> typeHandled() {
                return Boolean.class;
            }
        },
        BIGDECIMAL {
            @Override void set(int index, PreparedStatement stmt, Object value) throws SQLException {
                stmt.setBigDecimal(index, (BigDecimal) value);
            }

            @Override Class<?> typeHandled() {
                return BigDecimal.class;
            }
        },
        DATE {
            @Override void set(int index, PreparedStatement stmt, Object value) throws SQLException {
                stmt.setDate(index, (Date) value);
            }

            @Override Class<?> typeHandled() {
                return Date.class;
            }
        };

        abstract void set(int index, PreparedStatement stmt, Object value) throws SQLException;
        abstract Class<?> typeHandled();
    }
}
