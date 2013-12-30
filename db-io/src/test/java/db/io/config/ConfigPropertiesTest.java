package db.io.config;

import org.junit.Test;

import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;

public class ConfigPropertiesTest {
    @Test
    public void basicConfigPropTest() {
        TestConfig config = new TestConfig();

        assertThat(config.get(DBUrl.class).value(), notNullValue());
    }
}

class TestConfig extends DbConfig {
    @Override protected void config(DBContext context) {
        context.add(DBUrl.class, new DBUrl("jdbc:h2..."));
    }
}


