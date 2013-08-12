package dbtools.schema;

import dbtools.DBSchemaBaseListener;
import dbtools.DBSchemaParser;

import java.util.List;

import static com.google.common.collect.Lists.*;

class SchemaListener extends DBSchemaBaseListener {
    private static final List<ColDef> EMPTY_COLS = newArrayList();
    private List<TableDef> tables = newArrayList();
    private List<ColDef> columns = newArrayList();

    @Override
    public void exitTabledef(DBSchemaParser.TabledefContext ctx) {
        columns.clear();

        for (DBSchemaParser.ColdefContext colCtx : ctx.coldef()) {
            // TODO...
            //columns.add(...)
        }

        if (ctx.exception == null) {
            tables.add(new TableDef(ctx.tablename().getText(), EMPTY_COLS));
        }

    }

    public SchemaDef schema() {
        return new SchemaDef(tables);
    }

}
