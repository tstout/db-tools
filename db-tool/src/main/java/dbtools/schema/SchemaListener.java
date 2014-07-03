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
    private VersionBuilder versionBuilder = new VersionBuilder();

    @Override public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
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

    @Override public void enterMajorVersion(DBSchemaParser.MajorVersionContext ctx) {
        versionBuilder.majorVersion = Integer.parseInt(ctx.getText());
    }

    @Override public void enterMinorVersion(DBSchemaParser.MinorVersionContext ctx) {
        versionBuilder.minorVersion = Integer.parseInt(ctx.getText());
    }

    @Override public void enterPointVersion(DBSchemaParser.PointVersionContext ctx) {
        versionBuilder.pointVersion = Integer.parseInt(ctx.getText());
    }

    @Override public void enterVersionName(DBSchemaParser.VersionNameContext ctx) {
        versionBuilder.versionName = ctx.getText();
    }

    @Override public void enterDescrText(DBSchemaParser.DescrTextContext ctx) {
        versionBuilder.versionDescr = ctx.getText();
    }

    @Override
    public void exitTabledef(DBSchemaParser.TabledefContext ctx) {
        tableBuilder.withColumns(columns);
        tables.add(tableBuilder.build());
        columns.clear();
    }

    public SchemaDef schema() {
        return new SchemaDef(tables, versionBuilder.build());
    }

}
