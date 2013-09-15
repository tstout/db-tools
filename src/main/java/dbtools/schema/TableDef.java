package dbtools.schema;

import java.util.List;

import static com.google.common.collect.ImmutableList.copyOf;

public class TableDef {
    private final String name;
    private final List<ColDef> columns;

    public List<ColDef> columns() {
        return columns;
    }

    public String name() {
        return name;
    }

    public TableDef(String name, List<ColDef> columns) {
        this.name = name;
        this.columns = copyOf(columns);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private List<ColDef> columns;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withColumns(List<ColDef> columns) {
            this.columns = columns;
            return this;
        }

        public TableDef build() {
            return new TableDef(name, columns);
        }
    }
}
