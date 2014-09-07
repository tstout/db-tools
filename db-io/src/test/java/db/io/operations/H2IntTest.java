package db.io.operations;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import db.io.IntegrationTests;
import db.io.core.ConnFactory;
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
import static db.io.config.DBVendor.*;
import static db.io.config.Databases.*;
import static db.io.config.Resources.*;
import static db.io.migration.Migrators.*;
import static db.io.operations.Queries.*;
import static db.io.operations.Updates.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@Category(IntegrationTests.class)
public class H2IntTest {
    static final String INSERT_SQL = load("/db/io/migration/insert-log.sql");

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    ConnFactory conns = newConnFactory(H2_MEM, newCreds().withDBName("dbio-test"));

    long now = Calendar.getInstance().getTimeInMillis();

    @Before
    public void setup() {
        liquibase(conns)
            .update(getClass(), "/db/io/migration/test-changelog.sql");

        newUpdate(conns,
                INSERT_SQL,
                "test msg",
                "DEBUG",
                "test.logger",
                "test.thread")
                .run();
    }

    @After
    public void tearDown() {
        newUpdate(conns, "delete from db_io.logs")
                .run();
    }

    @Test
    public void basic_read_write() {
        Query q = newQuery(conns);

        Collection<LogRecord> result = q.run(LogRecord.class, "select * from db_io.logs");

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
