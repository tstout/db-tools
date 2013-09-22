package dbtools.cmdopts;

import org.junit.Test;

import java.util.Set;

import static com.google.common.collect.Sets.*;
import static dbtools.cmdopts.CmdRegistryTest.InvokedMethods.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CmdRegistryTest {
    ToolOptionParser parser = new ToolOptionParser();
    Set<InvokedMethods> invokedHandlers = newHashSet();

    enum InvokedMethods{
        CMD_ONE,
        CMD_TWO
    }

    @Test
    public void foo() {
        new CmdRegistry(this)
                .processCommand(parser.parse("cmd1 --opt1"));

        assertThat(invokedHandlers.contains(CMD_ONE), is(true));
    }

    @Command("cmd1")
    void cmdHandler1(Cmd cmd) {
        invokedHandlers.add(CMD_ONE);
    }

    @Command("cmd2")
    void cmdHandler2(Cmd cmd) {
        invokedHandlers.add(CMD_TWO);
    }
}
