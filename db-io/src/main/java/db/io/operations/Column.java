package db.io.operations;

import com.google.common.base.Objects;

class Column {
    private final Class<?> type;
    private final String name;
    private final Object value; // Optional<T> ?

    Column(Class<?> type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    String name() {
        return name;
    }

    Class<?> type() {
        return type;
    }

    <T> T val(Class<T> type) {
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
