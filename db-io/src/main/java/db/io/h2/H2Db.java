package db.io.h2;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import db.io.Database;
import db.io.config.DBCredentials;
import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;

import static com.google.common.base.Throwables.*;

// TODO - should this implement closable?
public class H2Db implements Database {
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

    @Override public Connection connection(DBCredentials creds) {
        try {
            return cache.get(creds).getConnection();
        } catch (Exception e) {
            throw propagate(e);
        }
    }
}




