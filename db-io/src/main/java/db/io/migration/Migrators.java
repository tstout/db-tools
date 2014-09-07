package db.io.migration;

import db.io.core.ConnFactory;

public final class Migrators {
    private Migrators() {}

    public static Migrator liquibase(ConnFactory connFactory) {
        return new LiquibaseMigrator(connFactory);
    }
}
