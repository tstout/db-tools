package db.io.operations;

import db.io.Database;
import db.io.config.ConnectionFactory;
import db.io.config.DBCredentials;

import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.*;
import static com.google.common.collect.ImmutableList.*;
import static com.google.common.collect.Lists.*;

public class UpdateBuilder {
    private ConnectionFactory factory;
    private Database db;
    private DBCredentials dbCreds;
    private List<UpdateOp> ops = newArrayList();

    public UpdateBuilder withCreds(DBCredentials dbCreds) {
        this.dbCreds = checkNotNull(dbCreds);
        return this;
    }

    public UpdateBuilder withDb(Database db) {
        this.db = checkNotNull(db);
        return this;
    }

    public UpdateBuilder addOp(String sql, Object... args) {
        ops.add(new UpdateOp(sql, args));
        return this;
    }

    public UpdateBuilder withConnFactory(ConnectionFactory factory) {
        this.factory = factory;
        return this;
    }


    public Update build() {
        Collection<UpdateOp> opsCopy = copyOf(ops);
        ops.clear();
        return new UpdateRunner(factory == null ? new ConnectionFactory(dbCreds, db) : factory,
                opsCopy);
    }
}

