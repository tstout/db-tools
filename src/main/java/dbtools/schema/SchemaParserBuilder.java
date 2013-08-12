package dbtools.schema;

import com.google.common.base.Optional;
import dbtools.CmdOptionsLexer;
import dbtools.DBSchemaParser;
import dbtools.parsing.SyntaxErrorListener;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class SchemaParserBuilder {
    private String schemaDef;
    private Optional<ANTLRErrorListener> listener = Optional.absent();

    public SchemaParserBuilder withDef(String schemaDef) {
        this.schemaDef = schemaDef;
        return this;
    }

    public SchemaParserBuilder withErrListener(ANTLRErrorListener listener) {
        this.listener = Optional.of(listener);
        return this;
    }

    public ParseTree build() {
        DBSchemaParser parser = new DBSchemaParser(tokenStream());
        parser.removeErrorListeners();
        parser.addErrorListener(listener.or(new SyntaxErrorListener()));

        return parser.schemadef();
    }

    ANTLRInputStream stream() {
        return new ANTLRInputStream(schemaDef.toCharArray(), schemaDef.length());
    }

    CmdOptionsLexer lexer() {
        return new CmdOptionsLexer(stream());
    }

    CommonTokenStream tokenStream() {
        return new CommonTokenStream(lexer());
    }
}
