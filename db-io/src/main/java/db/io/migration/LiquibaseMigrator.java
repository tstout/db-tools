package db.io.migration;

import db.io.core.ConnFactory;
import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import static com.google.common.base.Preconditions.*;
import static com.google.common.base.Throwables.*;
import static com.google.common.io.Resources.*;

class LiquibaseMigrator implements Migrator {
    private final Connection conn;

    LiquibaseMigrator(ConnFactory connFactory) {
        this.conn = connFactory.connection();
    }

    @Override public void update(String script) {
        update(getClass(), script);
    }

    @Override
    public void update(Class<?> root, String script) {
        checkNotNull(script);

        try {
            liquibase.database.Database database = DatabaseFactory
                    .getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(conn));

            new Liquibase(script,
                    new Loader(root), database)
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

class Loader extends ClassLoaderResourceAccessor {
    Class root;

    Loader(Class root) {
        this.root = root;
    }

    @Override public InputStream getResourceAsStream(String file) throws IOException {

        return asByteSource(
                getResource(root, file))
                .openStream();
    }
}
