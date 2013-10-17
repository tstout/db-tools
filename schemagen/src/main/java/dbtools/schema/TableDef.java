package dbtools.schema;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Optional.*;
import static com.google.common.collect.FluentIterable.*;
import static com.google.common.collect.ImmutableList.*;
import static com.google.common.collect.Lists.*;
import static dbtools.schema.Tables.*;

public class TableDef {
    public static final TableDef EMPTY = TableDef.builder().build();
    private static final List<ColDef> EMPTY_COLS = newArrayList();

    private final String name;
    private final List<ColDef> columns;

    public List<ColDef> columns() {
        return copyOf(columns);
    }

    //public Optional<ColDef> col(String name)
    public ColDef col(String name) {
        return from(columns)
                .firstMatch(colNameIs(name))
                .get();
    }

    public int numCols() {
        return columns.size();
    }

    public String name() {
        return name;
    }

    private TableDef(String name, List<ColDef> columns) {
        this.name = name;
        this.columns = copyOf(fromNullable(columns)
                .or(new ArrayList<ColDef>()));
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
