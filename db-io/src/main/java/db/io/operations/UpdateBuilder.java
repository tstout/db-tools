package db.io.operations;

import db.io.Database;
import db.io.config.DBCredentials;

import static com.google.common.base.Preconditions.checkNotNull;

public class UpdateBuilder {
    private Database db;
    private DBCredentials dbCreds;

    public UpdateBuilder withCreds(DBCredentials dbCreds) {
        this.dbCreds = dbCreds;
        return this;
    }

    public UpdateBuilder withDb(Database db) {
        this.db = db;
        return this;
    }

    public Update build() {
        checkNotNull(db);
        checkNotNull(dbCreds);
        return new UpdateRunner(db, dbCreds);
    }
}

