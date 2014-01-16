package db.io.h2;

import db.io.config.DBUrl;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static java.lang.String.format;

public class H2Url extends DBUrl {
    private static final String FMT = "jdbc:h2:%s:%s";

    private H2Url(String url) {
        super(url);
    }

    public static H2Url memDB(String dbName) {
        return new H2Url(format(FMT, "mem", dbName));
    }

    public static H2Url serverDB() {
        throw new NotImplementedException();
    }
}
