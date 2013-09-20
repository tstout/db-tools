package dbtools.schema;

import dbtools.DBSchemaBaseListener;
import dbtools.DBSchemaParser;
import org.antlr.v4.runtime.tree.ErrorNode;

import java.util.List;

import static com.google.common.collect.Lists.*;

class SchemaListener extends DBSchemaBaseListener {

    private List<TableDef> tables = newArrayList();
    private List<ColDef> columns = newArrayList();
    private TableDef.Builder tableBuilder;

    @Override public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void enterTabledef(DBSchemaParser.TabledefContext ctx) {
        tableBuilder = TableDef.builder()
            .withName(ctx.tablename().getText());
    }

    @Override
    public void exitColDef(DBSchemaParser.ColDefContext ctx) {
        ColDef.Builder builder = ColDef.builder();

        for (DBSchemaParser.ColAttributeContext attribCtx : ctx.colAttribute()) {
            builder.addAttribute(attribCtx.getText());
        }

        columns.add(builder
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
