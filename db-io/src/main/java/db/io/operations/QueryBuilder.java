package db.io.operations;

import db.io.Database;
import db.io.config.DBCredentials;

import static com.google.common.base.Preconditions.checkNotNull;

public class QueryBuilder {
    private Database db;
    private DBCredentials dbCreds;

    public QueryBuilder withCreds(DBCredentials dbCreds) {
        checkNotNull(dbCreds);
        this.dbCreds = dbCreds;
        return this;
    }

    public QueryBuilder withDb(Database db) {
        checkNotNull(db);
        this.db = db;
        return this;
    }

    public Query build() {
        return new QueryRunner(db, dbCreds);
    }
}
