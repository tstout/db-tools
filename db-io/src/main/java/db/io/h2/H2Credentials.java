package db.io.h2;

import db.io.config.DBCredentials;
import db.io.config.DBPwd;
import db.io.config.DBUser;

public final class H2Credentials {

    private H2Credentials() {
        throw new IllegalStateException("No H2Credendital instances allowed");
    }

    public static DBCredentials h2MemCreds(String dbName) {
        return new DBCredentials.Default(
                H2Url.memDB(dbName),
                new DBPwd(""),
                new DBUser("sa"));
    }

    public static DBCredentials h2LocalServerCreds(String dbName, String dir) {
        return new DBCredentials.Default(
                H2Url.localServerDB(dbName, dir),
                new DBPwd(""),
                new DBUser("sa"));
    }
}
