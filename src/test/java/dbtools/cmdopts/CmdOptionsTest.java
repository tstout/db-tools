package dbtools.cmdopts;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class CmdOptionsTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void multipleOptions() {
        Cmd cmd = new ToolOptionParser().parse("cmd --opt val1 --opt2 val2   -opt3");

        assertThat(cmd.name(), is("cmd"));
        assertThat(cmd.options().size(), is(3));
        assertThat(cmd.options().get(0).name(), is("opt"));
        assertThat(cmd.options().get(0).value().get(), is("val1"));
        assertThat(cmd.options().get(1).name(), is("opt2"));
        assertThat(cmd.options().get(1).value().get(), is("val2"));
        assertThat(cmd.options().get(2).name(), is("opt3"));
        assertThat(cmd.options().get(2).value().isPresent(), is(false));
    }

    @Test
    public void singleOption() {
        Cmd cmd = new ToolOptionParser().parse("cmdstr");

        assertThat(cmd.name(), is("cmdstr"));
        assertThat(cmd.options().size(), is(0));
    }

    @Test
    public void invalidOption() {
        thrown.expect(OptParseException.class);

        Cmd cmd = new ToolOptionParser().parse("--cmdstr");
    }

    @Test
    public void singleCmdNoOption() {
        Cmd cmd = new ToolOptionParser().parse("singlecmd");
        assertThat(cmd.options().size(), is(0));
        assertThat(cmd.name(), is("singlecmd"));
    }
}
