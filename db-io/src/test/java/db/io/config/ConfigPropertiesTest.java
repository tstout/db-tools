package db.io.config;

import db.io.UnitTests;
import db.io.h2.H2Url;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

@Category(UnitTests.class)
public class ConfigPropertiesTest {
    @Test
    public void basicConfigPropTest() {
        DbConfig config = new DbConfig() {{
            context.add(H2Url.class, H2Url.memDB("test_db"));
            configured();
        }};

        String url = config.get(H2Url.class).value();

        assertThat(url, notNullValue());
        assertThat(url, containsString("mem"));
        assertThat(url, containsString("test_db"));
    }
}


