package dbtools.cmdopts;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ToolOptionParser {
    ParseTreeWalker walker = new ParseTreeWalker();
    CmdOptListener listener = new CmdOptListener();

    public Cmd parse(String optString) {
        ParseTree tree = new OptParserBuilder()
                .withOpts(optString)
                .build();

        walker.walk(listener, tree);
        return listener.cmd();
    }
}
