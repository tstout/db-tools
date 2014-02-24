package db.io.migration;

import db.io.Database;
import db.io.config.DBCredentials;
import db.io.h2.H2Credentials;
import db.io.h2.H2Db;
import org.junit.Test;

import static db.io.migration.Migrators.liquibase;

public class MigratorTest {
    @Test
    public void update_log_schema() {
        Database db = new H2Db();
        //DBCredentials creds = H2Credentials.h2LocalServerCreds("dbio-test", "~/.dbio");
        DBCredentials creds = H2Credentials.h2MemCreds("test_db");
        liquibase().update("db/io/migration/test-changelog.sql", db.connection(creds));
    }
}
