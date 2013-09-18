package dbtools.schema;

import com.google.common.base.Predicate;

final class Tables {

    static Predicate<ColDef> colNameIs(final String name) {
        return new Predicate<ColDef>() {
            public boolean apply(dbtools.schema.ColDef input) {
                return input.name().equals(name);
            }
        };
    }
}
