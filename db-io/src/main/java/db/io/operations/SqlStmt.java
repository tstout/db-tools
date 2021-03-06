package db.io.operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.google.common.base.Throwables.*;

interface SqlStmt {
    PreparedStatement prepare(Connection conn, String sql, Object... args);

    static SqlStmt Default = new SqlStmt() {
        @Override
        public PreparedStatement prepare(Connection conn, String sql, Object... args) {
            try {
                // TODO - add parsing to handle named parms...
                return new ArgSetter()
                      .setValues(conn.prepareStatement(sql), args);
            } catch (SQLException e) {
                throw propagate(e);
            }
        }
    };
}
