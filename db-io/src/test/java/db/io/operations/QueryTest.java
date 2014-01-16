package db.io.operations;

import db.io.Database;
import db.io.SqlStmt;
import db.io.config.DBCredentials;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QueryTest {
    // TODO - a little heavy on the mocking here...short of loading up an in-memory DB, what else can be done?
    @Mock PreparedStatement stmt;
    @Mock Database db;
    @Mock DBCredentials creds;
    @Mock Connection conn;
    @Mock ResultSet rs;
    @Mock ResultSetMetaData rsMeta;

    @Before
    public void setup() throws Exception {
        when(conn.prepareStatement(any(String.class))).thenReturn(stmt);
        when(db.connection(any(DBCredentials.class))).thenReturn(conn);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.getMetaData()).thenReturn(rsMeta);
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("id")).thenReturn(1, 2);
        when(rs.getString("descr")).thenReturn("description val");
        when(rsMeta.getColumnCount()).thenReturn(2);
        when(rsMeta.getColumnName(1)).thenReturn("id");
        when(rsMeta.getColumnType(1)).thenReturn(Types.INTEGER);
        when(rsMeta.getColumnName(2)).thenReturn("descr");
        when(rsMeta.getColumnType(2)).thenReturn(Types.NVARCHAR);
    }

    @Test
    public void readSingleRowTest() throws SQLException {
        Query query = new DefaultQuery(db, creds);
        SqlStmt stmt = SqlStmt.Default;
        DataSet result = query.execute(stmt, "arbitrary sql...", SqlStmt.NO_ARGS);
        assertThat(result.numRows(), is(1));
        assertThat(result.get(0, "descr").val(String.class), is("description val"));
        assertThat(result.get(0, "id").val(Integer.class), is(1));

        verify(conn).close();
        verify(rs).close();
    }
}
