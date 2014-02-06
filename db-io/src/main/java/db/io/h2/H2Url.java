package db.io.h2;

import db.io.config.DBUrl;

import static java.lang.String.*;

public class H2Url extends DBUrl {
    private static final String FMT = "jdbc:h2:%s:%s";

    private H2Url(String url) {
        super(url);
    }

    public static H2Url memDB(String dbName) {
        return new H2Url(format(FMT, "mem", dbName));
    }

    public static H2Url localServerDB(String dbName, String dir) {
        String partialUrl = format("//127.0.0.1/%s/%s", dir, dbName);
        return new H2Url(format(FMT, "tcp", partialUrl));
    }
}
