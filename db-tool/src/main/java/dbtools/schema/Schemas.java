package dbtools.schema;

import com.google.common.base.Predicate;

final class Schemas {

    static Predicate<TableDef> tableNameIs(final String name) {
        return new Predicate<TableDef>() {
            public boolean apply(TableDef input) {
                return input.name().equals(name);
            }
        };
    }
}
