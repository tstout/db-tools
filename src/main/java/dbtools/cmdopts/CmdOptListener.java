package dbtools.cmdopts;

import dbtools.CmdOptionsBaseListener;
import dbtools.CmdOptionsParser;

public class CmdOptListener extends CmdOptionsBaseListener {

    private Cmd cmd;

    @Override
    public void exitCommand(CmdOptionsParser.CommandContext ctx) {
        Cmd.CmdBuilder cmdBuilder = Cmd.builder();
        cmdBuilder.withName(ctx.name().getText());

        for (CmdOptionsParser.OptionContext optContext : ctx.option()) {
            cmdBuilder.addOpt(
                    CmdOpt.builder()
                            .withName(optContext.name().getText())
                            .withValue(optContext.getText())
                            .build());
        }

        cmd = cmdBuilder.build();
    }
}
