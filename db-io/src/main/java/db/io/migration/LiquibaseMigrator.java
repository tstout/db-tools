package db.io.migration;

import db.io.config.ConnectionFactory;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;

import static com.google.common.base.Preconditions.*;
import static com.google.common.base.Throwables.*;

class LiquibaseMigrator implements Migrator {
    private final Connection conn;

    LiquibaseMigrator(ConnectionFactory connForge) {
        this.conn = checkNotNull(connForge).connection();
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
