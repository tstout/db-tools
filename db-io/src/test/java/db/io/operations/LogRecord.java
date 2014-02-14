package db.io.operations;

import java.sql.Timestamp;

public interface LogRecord {
    int id();
    Timestamp when();
    String msg();
    String level();
    String logger();
    String thread();
}
