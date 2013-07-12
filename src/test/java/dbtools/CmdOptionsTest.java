package dbtools;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class CmdOptionsTest {

    @Test
    public void foo() {
        CmdOptionsLexer lexer = new CmdOptionsLexer(new ANTLRInputStream());

        assertThat(1, is(1));
    }
}
