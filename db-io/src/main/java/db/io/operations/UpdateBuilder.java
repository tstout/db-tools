package db.io.operations;

import db.io.Database;
import db.io.config.DBCredentials;

import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.*;
import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Lists.*;

public class UpdateBuilder {
    private Database db;
    private DBCredentials dbCreds;
    private List<UpdateOp> ops = newArrayList();

    public UpdateBuilder withCreds(DBCredentials dbCreds) {
        this.dbCreds = dbCreds;
        return this;
    }

    public UpdateBuilder withDb(Database db) {
        this.db = db;
        return this;
    }

    public UpdateBuilder addOp(String sql, Object... args) {
        ops.add(new UpdateOp(sql, args));
        return this;
    }

    public Update build() {
        checkNotNull(db);
        checkNotNull(dbCreds);
        Collection<UpdateOp> opsCopy = copyOf(ops);
        ops.clear();
        return new UpdateRunner(db, dbCreds, opsCopy);
    }
}

