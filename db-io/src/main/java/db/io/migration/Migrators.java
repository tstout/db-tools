package db.io.migration;

import db.io.Database;
import db.io.config.DBCredentials;

public final class Migrators {

    private Migrators() {
        throw new UnsupportedOperationException();
    }

    public static Migrator liquibase(Database db, DBCredentials credentials) {
        return new LiquibaseMigrator(db, credentials);
    }
}
