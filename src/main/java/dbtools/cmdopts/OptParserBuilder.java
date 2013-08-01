package dbtools.cmdopts;

import dbtools.CmdOptionsLexer;
import dbtools.CmdOptionsParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
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

class DefaultErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {
        throw new RuntimeException(msg);
    }
}