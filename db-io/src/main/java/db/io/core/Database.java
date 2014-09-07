package db.io.core;

import db.io.config.DBCredentials;

import java.sql.Connection;

public interface Database {
    /**
     * Given some credentials, create a connection to a database.
     */
    Connection connection(DBCredentials creds);
}
