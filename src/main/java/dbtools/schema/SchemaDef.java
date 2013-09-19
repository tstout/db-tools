package dbtools.schema;

import java.util.List;

import static com.google.common.collect.FluentIterable.*;
import static com.google.common.collect.ImmutableList.*;
import static dbtools.schema.Schemas.*;

public class SchemaDef {
    private final List<TableDef> tables;

    public SchemaDef(List<TableDef> tables) {
        this.tables = copyOf(tables);
    }

    public TableDef table(String name) {
        return from(tables)
                .firstMatch(tableNameIs(name))
                .or(TableDef.EMPTY);
    }

    public List<ColDef> colsFor(String tableName) {
        return table(tableName).columns();
    }


    public int numTables() {
        return tables.size();
    }

    public List<TableDef> tables() {
        return tables;
    }
}
