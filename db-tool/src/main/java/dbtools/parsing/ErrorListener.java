package dbtools.parsing;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.util.BitSet;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ErrorListener extends BaseErrorListener {
    private List<String> errors = newArrayList();

    @Override
    public void reportAmbiguity(Parser recognizer,
                                DFA dfa,
                                int startIndex,
                                int stopIndex,
                                BitSet ambigAlts,
                                ATNConfigSet configs) {

        System.out.println("reportAmbiguity invoked!");
    }

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