package db.io.operations;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import db.io.Database;
import db.io.IntegrationTests;
import db.io.config.DBCredentials;
import db.io.h2.H2Db;
import db.io.migration.Migrators;
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
import static db.io.config.Credentials.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@Category(IntegrationTests.class)
public class H2IntTest {
    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    DBCredentials creds = h2Mem("dbio-test");
    Database db = new H2Db();

    long now = Calendar.getInstance().getTimeInMillis();

    UpdateBuilder uBuilder = new UpdateBuilder()
            .withCreds(creds)
            .withDb(db);

    @Before
    public void setup() {
        Migrators.liquibase(db, creds)
                .update("db/io/migration/test-changelog.sql");

        uBuilder.addOp("insert into db_io.logs (when, msg, level, logger, thread) values (?, ?, ?, ?, ?)",
                new Timestamp(now),
                "test msg",
                "DEBUG",
                "test.logger",
                "test.thread")
                .build()
                .update();
    }

    @After
    public void tearDown() {
        uBuilder.addOp("delete from db_io.logs")
                .build()
                .update();
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
