package db.io.config;

import static java.lang.String.*;

class H2Url extends DBUrl {
    private static final String FMT = "jdbc:h2:%s:%s";

    private H2Url(String url) {
        super(url);
    }

    static H2Url mem(DBName dbName) {
        return new H2Url(format(FMT, "mem", dbName.value()).concat(";DB_CLOSE_DELAY=-1"));
    }

    static H2Url localServer(DBName dbName, String dir) {
        String partialUrl = format("//127.0.0.1/%s/%s", dir, dbName);
        return new H2Url(format(FMT, "tcp", partialUrl));
    }

    static H2Url server(DBCredentials.Builder creds) {
        String partialUrl = format("//%s/%s/%s", creds.dbHost.value(), creds.dbDir.value(), creds.dbName);
        return new H2Url(format(FMT, "tcp", partialUrl));
    }
}
