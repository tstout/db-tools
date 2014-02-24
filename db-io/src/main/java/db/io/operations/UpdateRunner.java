package db.io.operations;

import db.io.Database;
import db.io.config.DBCredentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import static com.google.common.base.Preconditions.*;
import static com.google.common.base.Throwables.*;
import static com.google.common.collect.Lists.*;


class UpdateRunner implements Update {
    private final Database db;
    private final DBCredentials creds;
    private final SqlStmt stmtFactory = SqlStmt.Default;
    private final ArgSetter argSetter = new ArgSetter();
    private final Collection<UpdateOp> ops;

    UpdateRunner(Database db, DBCredentials creds, Collection<UpdateOp> ops) {
        this.db = checkNotNull(db);
        this.creds = checkNotNull(creds);
        this.ops = checkNotNull(ops);
    }

    @Override public Collection<Integer> update() {
        Collection<Integer> results = newArrayList();
        try (
                Connection conn = db.connection(creds)
        ) {
            conn.setAutoCommit(false);
            for (UpdateOp op : ops) {
                results.add(update(conn, op.sql(), op.args()));
            }
            conn.commit();
        } catch (Exception e) {
            throw propagate(e);
        }
        return results;
    }

    private int update(Connection conn, String sql, Object... args) {
        try (
                PreparedStatement statement = stmtFactory.prepare(conn, sql, args)
        ) {
            argSetter.setValues(statement, args);
            return statement.executeUpdate();
        } catch (Exception e) {
            rollback(conn);
            throw propagate(e);
        }
    }

    private void rollback(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw propagate(e);
        }
    }
}
