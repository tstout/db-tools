package dbtools.schema;

import com.google.common.collect.ImmutableBiMap;

public enum ColAttribute {
    PK("pk"),
    AUTOINC("autoinc");

    private String attributeName;

    private static final ImmutableBiMap<String, ColAttribute> valueMap =
            new ImmutableBiMap.Builder<String, ColAttribute>()
                    .put(PK.attributeName, PK)
                    .put(AUTOINC.attributeName, AUTOINC)
                    .build();

    ColAttribute(String attributeName) {
        this.attributeName = attributeName;
    }

    public static ColAttribute parse(String attributeName) {
        return valueMap.get(attributeName);
    }

}
