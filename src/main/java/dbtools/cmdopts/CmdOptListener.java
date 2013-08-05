package dbtools.cmdopts;

import com.google.common.base.Optional;
import dbtools.CmdOptionsBaseListener;
import dbtools.CmdOptionsParser;

import static com.google.common.base.Optional.fromNullable;

class CmdOptListener extends CmdOptionsBaseListener {
    private Cmd cmd;

    public Cmd cmd() {
        return cmd;
    }

    @Override
    public void exitCommand(CmdOptionsParser.CommandContext ctx) {
        Cmd.CmdBuilder cmdBuilder = Cmd.builder();
        cmdBuilder.withName(ctx.name().getText());

        for (CmdOptionsParser.OptionContext optContext : ctx.option()) {

            Optional<CmdOptionsParser.ValueContext> value =
                    fromNullable(optContext.value());

            cmdBuilder.addOpt(
                    CmdOpt.builder()
                            .withName(optContext.name().getText())
                            .withValue(fromNullable(value.isPresent() ? value.get().getText() : null))
                            .build());
        }

        cmd = cmdBuilder.build();
    }
}
