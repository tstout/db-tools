package db.io.operations;

import db.io.config.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import static com.google.common.base.Preconditions.*;
import static com.google.common.base.Throwables.*;

class QueryRunner implements Query {
    private final SqlStmt stmtFactory = SqlStmt.Default;
    private final ConnectionFactory connForge;

    QueryRunner(ConnectionFactory connForge) {
        this.connForge = checkNotNull(connForge);
    }

    @Override public <T> Collection<T> execute(Class<T> intf, String sql, Object... args) {
        try (
                Connection conn = connForge.connection();
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
        return new QueryMeta(rs)
                .newReader()
                .read(rs);
    }
}
