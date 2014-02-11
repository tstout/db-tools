package db.io.operations;

import com.google.common.primitives.Ints;
import db.io.UnitTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@Category(UnitTests.class)
public class ArgSetterTest {
    @Mock PreparedStatement stmt;
    @Mock Blob blob;

    byte[] bytes = Ints.toByteArray(0xCAFEBABE);

    @Test
    public void validate_each_setter() throws SQLException {
        Object[] args = {
                1,              // Integer
                true,           // Boolean
                "",             // String
                BigDecimal.ONE,
                new Date(now()),
                bytes,
                new Timestamp(now()),
                blob
        };

        ArgSetter argSetter = new ArgSetter();
        argSetter.setValues(stmt, args);

        verify(stmt).setInt(1, 1);
        verify(stmt).setBoolean(2, true);
        verify(stmt).setString(3, "");
        verify(stmt).setBigDecimal(4, BigDecimal.ONE);
        verify(stmt).setDate(any(Integer.class), any(Date.class));
        verify(stmt).setBytes(6, bytes);
        verify(stmt).setTimestamp(any(Integer.class), any(Timestamp.class));
        verify(stmt).setBlob(any(Integer.class), any(Blob.class));
    }

    long now() {
        return Calendar.getInstance().getTimeInMillis();
    }
}
