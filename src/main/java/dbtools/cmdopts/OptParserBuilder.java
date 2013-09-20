package dbtools.cmdopts;

import dbtools.CmdOptionsLexer;
import dbtools.CmdOptionsParser;
import dbtools.parsing.ThrowingErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import static com.google.common.base.Preconditions.*;

public class OptParserBuilder {
    private String optionString;

    public OptParserBuilder withOpts(String optionString) {
        this.optionString = optionString;
        return this;
    }

    public ParseTree build() {
        checkNotNull(optionString, "option string required");

        CmdOptionsParser parser = new CmdOptionsParser(tokenStream());
        parser.removeErrorListeners();
        parser.addErrorListener(new ThrowingErrorListener());

        return parser.command();
    }

    ANTLRInputStream stream() {
        return new ANTLRInputStream(optionString.toCharArray(), optionString.length());
    }

    CmdOptionsLexer lexer() {
        return new CmdOptionsLexer(stream());
    }

    CommonTokenStream tokenStream() {
        return new CommonTokenStream(lexer());
    }
}

