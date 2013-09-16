package dbtools.schema;

import com.google.common.collect.ImmutableBiMap;

public enum ColType {
    INT("int"),
    CHAR("char"),
    DECIMAL("decimal"),
    BOOL("bit");

    private String typeName;

    private ColType(String typeName) {
        this.typeName = typeName;
    }

    private static final ImmutableBiMap<String, ColType> valueMap =
            new ImmutableBiMap.Builder<String, ColType>()
                    .put(INT.typeName, INT)
                    .put(CHAR.typeName, CHAR)
                    .put(DECIMAL.typeName, DECIMAL)
                    .put(BOOL.typeName, BOOL)
                    .build();

    public static ColType parse(String typeName) {
        return valueMap.get(typeName);
    }
}
