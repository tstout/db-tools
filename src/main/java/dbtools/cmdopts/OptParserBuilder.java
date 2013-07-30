package dbtools.cmdopts;

import dbtools.CmdOptionsLexer;
import dbtools.CmdOptionsParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class OptParserBuilder {
    private String optionString;

    public OptParserBuilder withOpts(String optionString) {
        this.optionString = optionString;
        return this;
    }

    public ParseTree build() {
        return new CmdOptionsParser(tokenStream()).command();
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