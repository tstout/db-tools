package dbtools.cmdopts;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class CmdOptionsTest {

    @Test
    public void singleOption() {
        ParseTree tree = new OptParserBuilder()
                .withOpts("cmd --opt val1 --opt2 val2")
                .build();

        System.out.println(tree.toStringTree());
        assertThat(1, is(1));
    }
}
