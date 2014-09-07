package db.io.config;

import db.io.core.ConnFactory;
import db.io.core.Database;

import java.sql.Connection;

import static com.google.common.base.Preconditions.*;

class ConnectionFactoryImpl implements ConnFactory {
    private final DBCredentials creds;
    private final Database db;

    ConnectionFactoryImpl(DBCredentials creds, Database db) {
        this.db = checkNotNull(db);
        this.creds = checkNotNull(creds);
    }

    @Override
    public Connection connection() {
        return db.connection(creds);
    }
}
