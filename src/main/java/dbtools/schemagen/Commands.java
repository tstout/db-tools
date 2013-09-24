package dbtools.schemagen;

import dbtools.cmdopts.Cmd;
import dbtools.cmdopts.Command;
import dbtools.cmdopts.Docs;

public class Commands {
    @Command("help")
    public void showHelp(Cmd cmd) {
        System.out.println(Docs.HELP.text);
        System.exit(0);
    }


}

