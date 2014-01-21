package db.io.operations;

import db.io.UnitTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@Category(UnitTests.class)
public class ColFactoryTest {
    @Mock ResultSet rs;

    @Test
    public void readIntTest() throws Exception {
        when(rs.getInt(any(String.class))).thenReturn(7);
        assertThat(ColFactory.newColVal(rs, "", Integer.class), is(7));
    }

    @Test
    public void readStringTest() throws Exception {
        when(rs.getString(any(String.class))).thenReturn("test_val");
        assertThat(ColFactory.newColVal(rs, "", String.class), is("test_val"));
    }

    @Test
    public void readDateTest() throws Exception {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
        when(rs.getDate(any(String.class))).thenReturn(date);
        assertThat(ColFactory.newColVal(rs, "", java.sql.Date.class), is(date));
    }

    @Test
    public void readDateTimeStampTest() throws Exception {
        java.sql.Timestamp ts = new Timestamp(Calendar.getInstance().getTimeInMillis());
        when(rs.getTimestamp(any(String.class))).thenReturn(ts);
        assertThat(ColFactory.newColVal(rs, "", java.sql.Timestamp.class), is(ts));
    }
}
