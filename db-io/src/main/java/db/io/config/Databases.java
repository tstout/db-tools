package db.io.config;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import db.io.core.ConnFactory;
import db.io.core.Database;

import static com.google.common.base.Suppliers.*;
import static com.google.common.collect.ImmutableMap.*;
import static db.io.config.DBVendor.*;

public final class Databases {
    private Databases() {
        throw new UnsupportedOperationException();
    }

    /**
     * Create a new connection factory for a particular databse.
     */
    public static ConnFactory newConnFactory(DBVendor db, DBCredentials.Builder creds) {
        return new ConnectionFactoryImpl(newCreds(db, creds), newDB(db));
    }

    public static DBCredentials.Builder newCreds() {
        return new DBCredentials.Builder();
    }

    private static final ImmutableMap<DBVendor, Supplier<Database>> DATABASES =
            of(H2_REMOTE_SERVER, memoize(H2Db.supplier()),
                    H2_LOCAL_SERVER, memoize(H2Db.supplier()),
                    H2_MEM, memoize(H2Db.supplier()));

    private static final ImmutableMap<DBVendor, Function<DBCredentials.Builder, DBCredentials>> CREDS =
            of(H2_LOCAL_SERVER, H2Creds.localServerCreds(),
                    H2_REMOTE_SERVER, H2Creds.serverCreds(),
                    H2_MEM, H2Creds.memCreds());


    private static Database newDB(DBVendor db) {
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
