package db.io.operations;

import db.io.Database;
import db.io.config.ConnectionFactory;
import db.io.config.DBCredentials;

import static com.google.common.base.Preconditions.*;

// TODO - this seems redundant with respect to ConnectionFactory...
public class QueryBuilder {
    private Database db;
    private DBCredentials dbCreds;
    private ConnectionFactory factory;

    public QueryBuilder withCreds(DBCredentials dbCreds) {
        this.dbCreds = checkNotNull(dbCreds);
        return this;
    }

    public QueryBuilder withDb(Database db) {
        this.db = checkNotNull(db);
        return this;
    }

    public QueryBuilder withConnFactory(ConnectionFactory factory) {
        this.factory = factory;
        return this;
    }

    public Query build() {
        return new QueryRunner(factory == null ? new ConnectionFactory(dbCreds, db) : factory);
    }
}
