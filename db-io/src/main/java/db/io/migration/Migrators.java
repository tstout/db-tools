package db.io.migration;

import db.io.config.ConnectionFactory;

public final class Migrators {

    private Migrators() {
        throw new UnsupportedOperationException();
    }

    public static Migrator liquibase(ConnectionFactory connForge) {
        return new LiquibaseMigrator(connForge);
    }
}
