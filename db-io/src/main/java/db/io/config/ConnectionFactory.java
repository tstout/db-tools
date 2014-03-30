package db.io.config;

import db.io.Database;

import java.sql.Connection;

import static com.google.common.base.Preconditions.checkNotNull;

public class ConnectionFactory {
    private final DBCredentials creds;
    private final Database db;

    public ConnectionFactory(DBCredentials creds, Database db) {
        this.db = checkNotNull(db);
        this.creds = checkNotNull(creds);
    }

    public Connection connection() {
        return db.connection(creds);
    }
}
