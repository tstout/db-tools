package dbtools.schema;

import dbtools.DBSchemaParser;
import dbtools.parsing.SyntaxErrorListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class SchemaParser {
    private ParseTreeWalker walker = new ParseTreeWalker();
    private SchemaListener listener = new SchemaListener();
    private SyntaxErrorListener errListener = new SyntaxErrorListener();

    public SchemaDef parse(String schema) {
        walker.walk(listener, createParser(schema).schemadef());
        return listener.schema();
    }

    public void printTree(String schema) {
        DBSchemaParser parser = createParser(schema);
        System.out.println(parser.schemadef().toStringTree(parser));
    }

    public void printErrors() {
        errListener.printErrors();
    }

    private DBSchemaParser createParser(String schema) {
        return new SchemaParserBuilder()
                .withDef(schema)
                .withErrListener(errListener)
                .build();
    }
}
