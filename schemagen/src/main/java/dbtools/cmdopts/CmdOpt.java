package dbtools.cmdopts;

import com.google.common.base.Optional;

import static com.google.common.base.Optional.fromNullable;

public class CmdOpt {
    private final String name;
    private final String value;

    private CmdOpt(String name, String value) {
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
        return fromNullable(value);
    }

    public static class CmdOptBuilder {
        private String optName;
        private String value;

        public CmdOptBuilder withName(String optName) {
            this.optName = optName;
            return this;
        }

        public CmdOptBuilder withValue(String value) {
            this.value = value;
            return this;
        }

        public CmdOpt build() {
            return new CmdOpt(optName, value);
        }
    }
}
