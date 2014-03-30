package db.io.h2;

import db.io.config.DBUrl;

import static java.lang.String.*;

public class H2Urls extends DBUrl {
    private static final String FMT = "jdbc:h2:%s:%s";

    private H2Urls(String url) {
        super(url);
    }

    public static H2Urls memDB(String dbName) {
        return new H2Urls(format(FMT, "mem", dbName).concat(";DB_CLOSE_DELAY=-1"));
    }

    public static H2Urls localServerDB(String dbName, String dir) {
        String partialUrl = format("//127.0.0.1/%s/%s", dir, dbName);
        return new H2Urls(format(FMT, "tcp", partialUrl));
    }
}
