package dbtools.schemagen;

import dbtools.cmdopts.Cmd;
import dbtools.cmdopts.CmdEnv;
import dbtools.cmdopts.Command;
import dbtools.cmdopts.Docs;

import static dbtools.cmdopts.CmdEnv.Key.*;

public class Commands {
    private CmdEnv env;

    public Commands(CmdEnv env) {
        this.env = env;
    }

    @Command({"help", "options"})
    public void showHelp(Cmd cmd) {
        System.out.println(Docs.HELP.text);
        System.exit(0);
    }

    @Command("apply")
    public void apply(Cmd cmd) {
        env.put(SCHEMA, SCHEMA.valueFrom(cmd));
        env.put(SCHEMA_FILE, SCHEMA_FILE.valueFrom(cmd));
    }
}

