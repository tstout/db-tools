package db.io.operations;

import db.io.Database;
import db.io.SqlStmt;
import db.io.config.DBCredentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.google.common.base.Throwables.*;

class DefaultQuery implements Query {
    private final Database db;
    private final DBCredentials creds;

    DefaultQuery(Database db, DBCredentials creds) {
        this.db = db;
        this.creds = creds;
    }

    @Override public DataSet execute(SqlStmt stmt, String sql, Object... args) {
        try (
                Connection conn = db.connection(creds);
                PreparedStatement statement = stmt.prepare(conn, sql, args);
                ResultSet rs = statement.executeQuery()
        ) {
            return processResultSet(rs);
        } catch (SQLException e) {
            throw propagate(e);
        }
    }

    private DataSet processResultSet(ResultSet rs) {
        //
        // TODO - need to memoize the QueryMeta, probably need a query name here to id the query
        //
        return new QueryMeta(rs).newReader().read(rs);
    }
}
