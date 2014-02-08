package db.io.operations;

import db.io.Database;
import db.io.config.DBCredentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.google.common.base.Preconditions.*;
import static com.google.common.base.Throwables.*;


class UpdateRunner implements Update {
    private final Database db;
    private final DBCredentials creds;
    private final SqlStmt stmtFactory = SqlStmt.Default;
    private final ArgSetter argSetter = new ArgSetter();

    UpdateRunner(Database db, DBCredentials creds) {
        this.db = checkNotNull(db);
        this.creds = checkNotNull(creds);
    }

    @Override
    public int update(String sql, Object... args) {
        try (
                Connection conn = db.connection(creds);
                PreparedStatement statement = stmtFactory.prepare(conn, sql, args)
        ) {
            argSetter.setValues(statement, args);
            return statement.executeUpdate();

        } catch (SQLException e) {
            throw propagate(e);
        }
    }
}
