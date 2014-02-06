package db.io.operations;

import db.io.Database;
import db.io.config.DBCredentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Throwables.*;

class QueryRunner implements Query {
    private final Database db;
    private final DBCredentials creds;
    private final SqlStmt stmtFactory = SqlStmt.Default;

    QueryRunner(Database db, DBCredentials creds) {
        this.db = checkNotNull(db);
        this.creds = checkNotNull(creds);
    }

    @Override public <T> Collection<T> execute(Class<T> intf, String sql, Object... args) {
        try (
                Connection conn = db.connection(creds);
                PreparedStatement statement = stmtFactory.prepare(conn, sql, args);
                ResultSet rs = statement.executeQuery()
        ) {
            DataSet ds = processResultSet(rs);
            return new ValueFactory(intf, ds).create(intf, ds);

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
