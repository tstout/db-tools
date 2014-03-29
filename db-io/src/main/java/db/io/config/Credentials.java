package db.io.config;

import db.io.h2.H2Url;

public final class Credentials {
    private Credentials() {}

    public static CredentialBuilder newH2() {
        return new CredentialBuilder() {
            private DBPwd pwd = DBPwd.DEFAULT;
            private DBUrl url;
            private DBUser user;

            public CredentialBuilder withUser(DBUser user) {
                this.user = user;
                return this;
            }

            @Override public CredentialBuilder withUrl(DBUrl url) {
                this.url = url;
                return this;
            }

            @Override public CredentialBuilder withPwd(DBPwd pwd) {
                return this;
            }

            @Override public DBCredentials build() {
                return new DBCredentials.Default(url, pwd, user);
            }
        };
    }

    public static DBCredentials h2Mem(String dbName) {
        return new DBCredentials.Default(
                H2Url.memDB(dbName),
                DBPwd.DEFAULT,
                new DBUser("sa"));
    }

    public static DBCredentials h2LocalServer(String dbName, String dir) {
        return new DBCredentials.Default(
                H2Url.localServerDB(dbName, dir),
                new DBPwd(""),
                new DBUser("sa"));
    }
}
