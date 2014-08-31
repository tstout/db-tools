package db.io.config;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import db.io.core.ConnFactory;
import db.io.core.Database;

import static com.google.common.base.Suppliers.*;
import static com.google.common.collect.ImmutableMap.*;
import static db.io.config.Databases.DBVendor.*;

public final class Databases {
    private Databases() {
    }

    /**
     * Supported Databases
     */
    public enum DBVendor {
        /**
         * The H2 database, brought to you by Thomas Mueller and friends.
         */
        H2_SERVER,
        H2_LOCAL_SERVER,
        H2_MEM
    }

    private static final ImmutableMap<DBVendor, Supplier<Database>> DATABASES =
            of(H2_SERVER, memoize(H2Db.supplier()),
               H2_LOCAL_SERVER, memoize(H2Db.supplier()),
               H2_MEM, memoize(H2Db.supplier()));

    private static final ImmutableMap<DBVendor, Function<DBCredentials.Builder, DBCredentials>> CREDS =
            of(H2_LOCAL_SERVER, H2Creds.localServerCreds(),
               H2_SERVER, H2Creds.serverCreds(),
               H2_MEM, H2Creds.memCreds());

    public static ConnFactory newConnFactory(DBVendor db, DBCredentials.Builder creds) {
        return new ConnectionFactoryImpl(newCreds(db, creds), newDB(db, creds));
    }

    public static DBCredentials.Builder newCreds() {
        return new DBCredentials.Builder();
    }

    private static Database newDB(DBVendor db, DBCredentials.Builder creds) {
        return DATABASES
                .get(db)
                .get();
    }

    private static DBCredentials newCreds(DBVendor db, DBCredentials.Builder creds) {
        return CREDS
                .get(db)
                .apply(creds);
    }
}
