package dbtools.schemagen;

import dbtools.cmdopts.CmdEnv;
import dbtools.cmdopts.CmdRegistry;
import dbtools.cmdopts.ToolOptionParser;
import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class CommandEnvTest {
    CmdEnv env = new CmdEnv();

    @Test
    public void schemaName() {
        new CmdRegistry(new Commands(env))
                .process(new ToolOptionParser()
                        .parse("apply --schema test_schema --schemaFile x.schema"));

        assertThat(env.get(CmdEnv.Key.SCHEMA, String.class), is("test_schema"));
        assertThat(env.get(CmdEnv.Key.SCHEMA_FILE, String.class), is("x.schema"));
    }
}
