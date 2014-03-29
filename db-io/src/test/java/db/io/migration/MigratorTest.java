package db.io.migration;

import db.io.config.Credentials;
import db.io.h2.H2Db;
import org.junit.Test;

import static db.io.migration.Migrators.*;

public class MigratorTest {
    @Test
    public void update_log_schema() {
        liquibase(new H2Db(), Credentials.h2Mem("test_db"))
                .update("db/io/migration/test-changelog.sql");
    }
}
