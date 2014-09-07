package db.io.config;

import com.google.common.base.Supplier;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import db.io.core.Database;
import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;

import static com.google.common.base.Throwables.*;

// TODO - should this implement closable?
class H2Db implements Database {
    //
    // Might need to be more sophisticated regarding connection pooling.
    // Let's see how this simple implementation plays out.
    //
    static Supplier<Database> supplier() {
        return new Supplier<Database>() {
            public Database get() {
                return new H2Db();
            }
        };
    }

    private final CacheLoader<DBCredentials, JdbcConnectionPool> loader =
            new CacheLoader<DBCredentials, JdbcConnectionPool>() {

                @Override public JdbcConnectionPool load(DBCredentials key) throws Exception {
                    return JdbcConnectionPool.create(
                            key.url().value(),
                            key.user().value(),
                            key.pwd().value());
                }
            };

    private final LoadingCache<DBCredentials, JdbcConnectionPool> cache =
            CacheBuilder
                    .newBuilder()
                    .build(loader);

    @Override public Connection connection(DBCredentials cred) {
        try {
            return cache.get(cred).getConnection();
        } catch (Exception e) {
            throw propagate(e);
        }
    }
}




