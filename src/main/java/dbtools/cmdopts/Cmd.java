package dbtools.cmdopts;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

public class Cmd {
    private final List<CmdOpt> options;
    private final String name;

    public static CmdBuilder builder() {
        return new CmdBuilder();
    }

    private Cmd(String name, List<CmdOpt> options) {
        this.name = name;
        this.options = options;
    }

    public String name() {
        return name;
    }

    public List<CmdOpt> options() {
        return options;
    }

    public static class CmdBuilder {
        private String name;
        private List<CmdOpt> options = newArrayList();

        public CmdBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CmdBuilder addOpt(CmdOpt cmdOpt) {
            options.add(cmdOpt);
            return this;
        }

        public Cmd build() {
            checkNotNull(name, "name is required");
            return new Cmd(name, options);
        }
    }
}
