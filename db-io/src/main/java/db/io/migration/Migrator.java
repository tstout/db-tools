package db.io.migration;

import java.sql.Connection;

public interface Migrator {
    void update(Connection dbConn);
}