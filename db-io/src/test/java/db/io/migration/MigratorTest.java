package db.io.migration;

import db.io.config.ConnectionFactory;
import db.io.h2.H2Db;
import org.junit.Test;

import java.sql.SQLException;

import static db.io.h2.H2Credentials.h2MemCreds;
import static db.io.migration.Migrators.*;

public class MigratorTest {
    @Test
    public void update_log_schema() throws SQLException {
        ConnectionFactory connForge = new ConnectionFactory(h2MemCreds("test_db"), new H2Db());
        liquibase(connForge).update("db/io/migration/test-changelog.sql");

        //DatabaseMetaData meta = db.connection(creds).getMetaData();

    }
}
