package dbtools.schema;

import dbtools.DBSchemaBaseListener;
import dbtools.DBSchemaParser;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

import static com.google.common.collect.Lists.*;

class SchemaListener extends DBSchemaBaseListener {
    private static final List<ColDef> EMPTY_COLS = newArrayList();
    private List<TableDef> tables = newArrayList();
    private List<ColDef> columns = newArrayList();

    @Override
    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        super.visitTerminal(node);
    }

    @Override
    public void enterTabledef(DBSchemaParser.TabledefContext ctx) {
        super.enterTabledef(ctx);
    }

    @Override
    public void exitColDef(DBSchemaParser.ColDefContext ctx) {
        ctx.colName().getText();
        ctx.colType().getText();
    }

    @Override
    public void exitTabledef(DBSchemaParser.TabledefContext ctx) {
        columns.clear();



//        for (DBSchemaParser.ColdefContext colCtx : ctx.coldef()) {
//            for (ParseTree tree : colCtx.children) {
//
//            }
//                System.out.println(tree.getText());
//            }
//
//            System.out.println(colCtx.colname().NAME());
//            System.out.println(colCtx.coltype().getText());
//            //colCtx.NAME();
//
//            // TODO...
//            //columns.add(...)
//        //}

//        if (ctx.exception == null) {
//            tables.add(new TableDef(ctx.tablename().getText(), EMPTY_COLS));
//        }

    }

    public SchemaDef schema() {
        return new SchemaDef(tables);
    }

}
