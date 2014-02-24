package db.io.migration;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;

import static com.google.common.base.Preconditions.*;
import static com.google.common.base.Throwables.*;

public final class Migrators {

    private Migrators() {
        throw new UnsupportedOperationException();
    }

    public static Migrator liquibase() {
        return new Migrator() {
            @Override
            public void update(String script, Connection dbConn) {
                checkNotNull(script);
                checkNotNull(dbConn);

                try {
                    Database database = DatabaseFactory
                            .getInstance()
                            .findCorrectDatabaseImplementation(new JdbcConnection(dbConn));

                    Liquibase liquibase = new Liquibase(script,
                            new ClassLoaderResourceAccessor(),
                            database);

                    liquibase.changeLogSync(script);
                } catch (Exception e) {
                    throw propagate(e);
                }
            }
        };
    }
}
