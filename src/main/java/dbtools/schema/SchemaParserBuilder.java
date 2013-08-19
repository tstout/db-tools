package dbtools.schema;

import com.google.common.base.Optional;
import dbtools.DBSchemaLexer;
import dbtools.DBSchemaParser;
import dbtools.parsing.SyntaxErrorListener;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import static com.google.common.base.Preconditions.*;

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

    public DBSchemaParser build() {
        checkNotNull(schemaDef, "schema dbtools.schema.input required");

        DBSchemaParser parser = new DBSchemaParser(tokenStream());
        parser.removeErrorListeners();
        parser.addErrorListener(listener.or(new SyntaxErrorListener()));

        return parser;
    }

    ANTLRInputStream stream() {
        return new ANTLRInputStream(schemaDef);
    }

    DBSchemaLexer lexer() {
        return new DBSchemaLexer(stream());
    }

    CommonTokenStream tokenStream() {
        return new CommonTokenStream(lexer());
    }
}
