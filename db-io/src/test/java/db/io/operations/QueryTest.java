package db.io.operations;

import db.io.UnitTests;
import db.io.core.ConnFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;

import static com.google.common.collect.FluentIterable.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@Category(UnitTests.class)
public class QueryTest {
    // TODO - a little heavy on the mocking here...
    @Mock PreparedStatement stmt;
    @Mock Connection conn;
    @Mock ResultSet rs;
    @Mock ResultSetMetaData rsMeta;
    @Mock ConnFactory connFactory;

    Query query;

    @Before
    public void set_up() throws Exception {
        when(conn.prepareStatement(any(String.class))).thenReturn(stmt);
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
        when(connFactory.connection()).thenReturn(conn);
        query = new QueryRunner(connFactory);
    }

    interface SomeData {
        String descr();
        int id();
    }

    @Test
    public void read_a_single_row() throws SQLException {
        Collection<SomeData> result =
                query.run(SomeData.class,
                        "sql returning columns matching methods in SomeData...");

        assertThat(result.size(), is(1));

        SomeData data = from(result)
                .first()
                .get();

        assertThat(data.descr(), is("description val"));
        assertThat(data.id(), is(1));

        verify(conn).close();
        verify(rs).close();
    }
}
