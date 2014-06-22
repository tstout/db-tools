package db.io.h2;

import db.io.config.DBCredentials;
import db.io.config.DBPwd;
import db.io.config.DBUser;

public final class H2Credentials {

    private H2Credentials() {
    }
    // TODO - need user/pass version...
    public static DBCredentials h2MemCreds(String dbName) {
        return new DBCredentials.Default(
                H2Urls.memDB(dbName),
                new DBPwd(""),
                new DBUser("sa"));
    }

    public static DBCredentials h2LocalServerCreds(String dbName, String dir) {
        return new DBCredentials.Default(
                H2Urls.localServerDB(dbName, dir),
                new DBPwd(""),
                new DBUser("sa"));
    }
}
