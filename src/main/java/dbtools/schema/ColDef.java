package dbtools.schema;

public class ColDef {
    private final ColType type;
    private final String name;

    private ColDef(ColType type, String name) {
        this.type = type;
        this.name = name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ColType type() {
        return type;
    }

    public String name() {
        return name;
    }

    public static class Builder {
        private ColType type;
        private String name;

        public Builder withType(String type) {
            this.type = ColType.parse(type);
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public ColDef build() {
            return new ColDef(type, name);
        }
    }
}
