package db.io.operations;

import db.io.IntegrationTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@Category(IntegrationTests.class)
public class QueryIntTest {
    @Test
    public void foo() {
        assertThat(true, is(true));
    }
}
