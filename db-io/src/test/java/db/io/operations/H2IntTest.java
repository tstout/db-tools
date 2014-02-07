package db.io.operations;

import db.io.IntegrationTests;
import db.io.config.DBCredentials;
import db.io.h2.H2Db;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Collection;

import static com.google.common.collect.FluentIterable.from;
import static db.io.h2.H2Credentials.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@Category(IntegrationTests.class)
public class H2IntTest {

    interface LogRecord {
        int id();
        String msg();
    }

    @Test
    public void basic_read_write() {
        DBCredentials creds = h2LocalServerCreds("dbio-test", "~/.dbio");

        Update u = new UpdateBuilder()
                .withCreds(creds)
                .withDb(new H2Db())
                .build();

        u.update("insert into db_io.logs (msg) values ('test msg')");

        Query q = new QueryBuilder()
                .withCreds(creds)
                .withDb(new H2Db())
                .build();

        Collection<LogRecord> result = q.execute(LogRecord.class, "select * from db_io.logs");

        u.update("delete from db_io.logs");

        assertThat(result.size(), not(0));
        assertThat(from(result).first().get().msg(), is("test msg"));
    }
}
