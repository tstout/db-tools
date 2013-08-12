package dbtools.schema;

import java.util.List;

import static com.google.common.collect.ImmutableList.copyOf;

public class SchemaDef {
    private final List<TableDef> tables;

    public SchemaDef(List<TableDef> tables) {
        this.tables = copyOf(tables);
    }

    List<TableDef> tables() {
        return tables;
    }
}
