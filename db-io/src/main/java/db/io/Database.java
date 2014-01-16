package db.io;

import db.io.config.DBCredentials;

import java.sql.Connection;

public interface Database {
    Connection connection(DBCredentials creds);
}
