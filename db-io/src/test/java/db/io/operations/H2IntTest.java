package db.io.operations;

import db.io.Database;
import db.io.IntegrationTests;
import db.io.h2.H2Db;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static db.io.h2.H2Credentials.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@Category(IntegrationTests.class)
public class H2IntTest {

    Database db = new H2Db();

    @Test
    public void basic_select() {
    Query q = new QueryBuilder()
            .withCreds(h2LocalServerCreds("dbio-test", "~/.dbio"))
            .withDb(new H2Db())
            .build();

        q.execute("select * from db_io.logs");
        //
        assertThat(true, is(true));
    }
}
