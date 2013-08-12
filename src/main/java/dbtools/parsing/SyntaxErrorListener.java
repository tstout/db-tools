package dbtools.parsing;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class SyntaxErrorListener extends BaseErrorListener {
    private List<String> errors = newArrayList();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {
        errors.add(String.format("%d:%d %s", line, charPositionInLine, msg));
    }

    public void printErrors() {
        for (String err : errors) {
            System.out.println(err);
        }
    }
}