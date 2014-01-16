package db.io.config;

import db.io.h2.H2Url;
import org.junit.Test;

import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

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


