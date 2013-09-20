package dbtools.schemagen;

import com.google.common.base.Joiner;
import dbtools.cmdopts.Cmd;
import dbtools.cmdopts.ToolOptionParser;

public class Main {
    public static void main(String[] args) {

        if (args.length == 0) {
            // show help
            System.exit(0);
        }

        Cmd cmd = new ToolOptionParser().parse(Joiner.on(' ').join(args));


    }
}
