package db.io.operations;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import db.io.IntegrationTests;
import db.io.config.DBCredentials;
import db.io.h2.H2Db;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;

import static com.google.common.collect.FluentIterable.*;
import static db.io.h2.H2Credentials.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@Category(IntegrationTests.class)
public class H2IntTest {
    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    DBCredentials creds = h2LocalServerCreds("dbio-test", "~/.dbio");

    long now = Calendar.getInstance().getTimeInMillis();

    Update u = new UpdateBuilder()
            .withCreds(creds)
            .withDb(new H2Db())
            .build();

    @Before
    public void setup() {
        u.update("insert into db_io.logs (when, msg, level, logger, thread) values (?, ?, ?, ?, ?)",
                new Timestamp(now),
                "test msg",
                "DEBUG",
                "test.logger",
                "test.thread");
    }

    @After
    public void tearDown() {
        u.update("delete from db_io.logs");
    }

    @Test
    public void basic_read_write() {
        Query q = new QueryBuilder()
                .withCreds(creds)
                .withDb(new H2Db())
                .build();

        Collection<LogRecord> result = q.execute(LogRecord.class, "select * from db_io.logs");

        assertThat(result.size(), not(0));

        LogRecord record = from(result).first().get();

        assertThat(record.id(), not(0));
        assertThat(record.level(), is("DEBUG"));
        assertThat(record.logger(), is("test.logger"));
        assertThat(record.thread(), is("test.thread"));
        assertThat(record.msg(), is("test msg"));
        assertThat(record.when(), is(new Timestamp(now)));
    }
}
