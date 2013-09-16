package dbtools.schema;

import dbtools.DBSchemaBaseListener;
import dbtools.DBSchemaParser;

import java.util.List;

import static com.google.common.collect.Lists.*;

class SchemaListener extends DBSchemaBaseListener {

    private List<TableDef> tables = newArrayList();
    private List<ColDef> columns = newArrayList();
    private TableDef.Builder tableBuilder;

    @Override
    public void enterTabledef(DBSchemaParser.TabledefContext ctx) {
        tableBuilder = TableDef.builder()
            .withName(ctx.tablename().getText());
    }

    @Override
    public void exitColDef(DBSchemaParser.ColDefContext ctx) {
        columns.add(ColDef.builder()
                .withName(ctx.colName().getText())
                .withType(ctx.colType().getText())
                .build());
    }

    @Override
    public void exitTabledef(DBSchemaParser.TabledefContext ctx) {
        tableBuilder.withColumns(columns);
        tables.add(tableBuilder.build());
        columns.clear();
    }

    public SchemaDef schema() {
        return new SchemaDef(tables);
    }

}
