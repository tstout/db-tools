package dbtools.schema;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import static dbtools.schema.TestInput.*;

public class SchemaParseTest {
    @Test
    public void parseEmptyTable() {

        SchemaDef def = new SchemaParser().parse(EMPTY_TABLE.input());
        new SchemaParser().printTree(EMPTY_TABLE.input());

        assertThat(def.tables().size(), not(0));
        assertThat(def.tables().get(0).name(), is("table_name"));
    }
}
