package db.io.migration;

import db.io.Database;
import db.io.config.DBCredentials;

public final class Migrators {
    private Migrators() { }

    public static Migrator liquibase(Database db, DBCredentials creds) {
        return new LiquibaseMigrator(db, creds);
    }
}
