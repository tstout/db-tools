package db.io.migration;

import db.io.Database;
import db.io.config.DBCredentials;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Throwables.propagate;

class LiquibaseMigrator implements Migrator {
    private final Connection conn;

    LiquibaseMigrator(Database db, DBCredentials credentials) {
        conn = checkNotNull(db.connection(credentials));
    }

    @Override
    public void update(String script) {
        checkNotNull(script);

        try {
            liquibase.database.Database database = DatabaseFactory
                    .getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(conn));

            new Liquibase(script,
                    new ClassLoaderResourceAccessor(), database)
                    .update(script);

            if (!conn.getAutoCommit()) {
                conn.rollback();
            }

            conn.close();

        } catch (Exception e) {
            throw propagate(e);
        }
    }
}
