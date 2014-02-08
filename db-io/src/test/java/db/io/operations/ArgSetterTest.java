package db.io.operations;

import db.io.UnitTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@Category(UnitTests.class)
public class ArgSetterTest {
    @Mock PreparedStatement stmt;

    @Test
    public void validate_each_setter() throws SQLException {
        Object[] args = {
                1,              // Integer
                true,           // Boolean
                "",             // String
                BigDecimal.ONE,
                new Date(Calendar.getInstance().getTimeInMillis())
        };

        ArgSetter argSetter = new ArgSetter();
        argSetter.setValues(stmt, args);

        verify(stmt).setInt(0, 1);
        verify(stmt).setBoolean(1, true);
        verify(stmt).setString(2, "");
        verify(stmt).setBigDecimal(3, BigDecimal.ONE);
        verify(stmt).setDate(any(Integer.class), any(Date.class));
    }
}
