package db.io.core;

import javax.sql.DataSource;
import java.sql.Connection;

public interface ConnFactory {
    Connection connection();
    DataSource dataSource();
}
