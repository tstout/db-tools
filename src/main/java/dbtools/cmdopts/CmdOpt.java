package dbtools.cmdopts;

import com.google.common.base.Optional;

public class CmdOpt {
    private final String name;
    private final Optional<String> value;

    private CmdOpt(String name, Optional<String> value) {
        this.name = name;
        this.value = value;
    }

    public static CmdOptBuilder builder() {
        return new CmdOptBuilder();
    }

    public String name() {
        return name;
    }

    public Optional<String> value() {
        return value;
    }

    public static class CmdOptBuilder {
        private String optName;
        private Optional<String> value = Optional.absent();

        public CmdOptBuilder withName(String optName) {
            this.optName = optName;
            return this;
        }

        public CmdOptBuilder withValue(Optional<String> value) {
            this.value = value;
            return this;
        }

        public CmdOpt build() {
            return new CmdOpt(optName, value);
        }
    }
}
