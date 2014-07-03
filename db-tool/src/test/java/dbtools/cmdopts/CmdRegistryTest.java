package dbtools.cmdopts;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Set;

import static com.google.common.collect.Sets.*;
import static dbtools.cmdopts.CmdRegistryTest.InvokedMethods.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class CmdRegistryTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    ToolOptionParser parser = new ToolOptionParser();
    Set<InvokedMethods> invokedHandlers = newHashSet();

    enum InvokedMethods{
        CMD_ONE,
        CMD_TWO
    }

    @Test
    public void foo() {
        new CmdRegistry(this)
                .process(parser.parse("cmd1 --opt1 -opt2"));

        new CmdRegistry(this)
                .process(parser.parse("cmd2 --opt1 -opt2"));

        assertThat(invokedHandlers.contains(CMD_TWO), is(true));
        assertThat(invokedHandlers.contains(CMD_ONE), is(true));
    }

    @Test
    public void invalidCmd() {
        thrown.expect(IllegalArgumentException.class);

        new CmdRegistry(this)
                .process(parser.parse("unknown-option"));
    }

    @Command("cmd1")
    void cmdHandler1(Cmd cmd) {
        invokedHandlers.add(CMD_ONE);
        assertThat(cmd.options().size(), is(2));
    }

    @Command("cmd2")
    void cmdHandler2(Cmd cmd) {
        invokedHandlers.add(CMD_TWO);
        assertThat(cmd.options().size(), is(2));
    }
}
