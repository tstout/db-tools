package db.io.operations;

import com.google.common.base.Objects;

public class Column {
    private final Class<?> type;
    private final String name;
    private final Object value; // Nullable<T> ?

    public Column(Class<?> type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String name() {
        return name;
    }

    public Class<?> type() {
        return type;
    }

//    public Column copy(Object value) {
//        return new Column(type, name, value);
//    }

    public <T> T val(Class<T> type) {
        return type.cast(value);
    }

    @Override public int hashCode() {
        return Objects.hashCode(type, value, name);
    }

    @Override public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Column other = (Column) obj;

        return Objects.equal(this.type, other.type) &&
               Objects.equal(this.value, other.value) &&
               Objects.equal(this.name, other.name);
    }
}
