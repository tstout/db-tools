package dbtools.schema;

import java.util.List;

import static com.google.common.collect.FluentIterable.*;
import static com.google.common.collect.ImmutableList.*;
import static dbtools.schema.Schemas.*;

public class SchemaDef {
    private final List<TableDef> tables;
    private final ChangeLog changeLog;

    public ChangeLog changeLog() {
        return changeLog;
    }

    public SchemaDef(List<TableDef> tables, ChangeLog changeLog) {
        this.tables = copyOf(tables);
        this.changeLog = changeLog;
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
