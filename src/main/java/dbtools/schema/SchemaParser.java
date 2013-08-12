package dbtools.schema;

import dbtools.parsing.SyntaxErrorListener;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class SchemaParser {
    ParseTreeWalker walker = new ParseTreeWalker();
    SchemaListener listener = new SchemaListener();
    SyntaxErrorListener errListener = new SyntaxErrorListener();

    public SchemaDef parse(String schema) {
        ParseTree tree = new SchemaParserBuilder()
                .withDef(schema)
                .withErrListener(errListener)
                .build();

        walker.walk(listener, tree);
        return listener.schema();
    }

    public void printErrors() {
        errListener.printErrors();
    }
}
