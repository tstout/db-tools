package dbtools.schemagen;

import com.google.common.base.Joiner;
import dbtools.cmdopts.CmdRegistry;
import dbtools.cmdopts.ToolOptionParser;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            new Commands().showHelp(null);
        }

        new CmdRegistry(new Commands()).processCommand(new ToolOptionParser()
                .parse(Joiner.on(' ').join(args)));
    }
}


