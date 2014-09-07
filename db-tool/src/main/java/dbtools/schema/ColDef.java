package dbtools.schema;

import java.util.List;

import static com.google.common.collect.ImmutableList.*;
import static com.google.common.collect.Lists.*;

public class ColDef {
    private final ColType type;
    private final String name;
    private final List<ColAttribute> attributes;

    private ColDef(ColType type, String name, List<ColAttribute> attributes) {
        this.type = type;
        this.name = name;
        this.attributes = attributes;
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

    public List<ColAttribute> attributes() {
        return copyOf(attributes);
    }

    public static class Builder {
        private ColType type;
        private String name;
        private List<ColAttribute> attributes = newArrayList();

        public Builder withType(String type) {
            this.type = ColType.parse(type);
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder addAttribute(String name) {
            attributes.add(ColAttribute.parse(name));
            return this;
        }

        public ColDef build() {
            return new ColDef(type, name, attributes);
        }
    }
}
