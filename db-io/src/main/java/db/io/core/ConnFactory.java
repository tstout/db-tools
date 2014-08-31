package db.io.core;

import java.sql.Connection;

public interface ConnFactory {
    Connection connection();
}
