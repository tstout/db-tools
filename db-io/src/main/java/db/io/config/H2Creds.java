package db.io.config;

import com.google.common.base.Function;

final class H2Creds {

    private H2Creds() {}

    static Function<DBCredentials.Builder, DBCredentials> memCreds() {
        return new Function<DBCredentials.Builder, DBCredentials>() {

            @Override public DBCredentials apply(DBCredentials.Builder input) {
                return input
                        .withUrl(H2Url.mem(input.dbName))
                        .build();
            }
        };
    }

    static Function<DBCredentials.Builder, DBCredentials> localServerCreds() {
        return new Function<DBCredentials.Builder, DBCredentials>() {

            @Override public DBCredentials apply(DBCredentials.Builder input) {
                return input
                        .withUrl(H2Url.localServer(input.dbName, input.dbDir.value()))
                        .build();
            }
        };
    }

    static Function<DBCredentials.Builder, DBCredentials> serverCreds() {
        return new Function<DBCredentials.Builder, DBCredentials>() {

            @Override public DBCredentials apply(DBCredentials.Builder input) {
                return input
                        .withUrl(H2Url.server(input))
                        .build();
            }
        };
    }
}
