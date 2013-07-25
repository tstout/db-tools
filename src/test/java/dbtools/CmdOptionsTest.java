package dbtools;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class CmdOptionsTest {

    @Test
    public void singleOption() {
        ParseTree tree = new OptParserBuilder()
                .withOpts("--opt423 4 --a --c 2")
                .build();

        System.out.println(tree.toStringTree());
        assertThat(1, is(1));
    }

    class OptParserBuilder {
        private String optionString;

        public OptParserBuilder withOpts(String optionString) {
            this.optionString = optionString;
            return this;
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

        public ParseTree build() {
            return new CmdOptionsParser(tokenStream()).init();

        }
    }
}
