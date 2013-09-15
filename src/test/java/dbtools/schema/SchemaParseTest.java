package dbtools.schema;

import org.junit.Test;

import static dbtools.schema.TestInput.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SchemaParseTest {
    @Test
    public void parseEmptyTable() {

        SchemaDef def = new SchemaParser().parse(EMPTY_TABLE.input());
        new SchemaParser().printTree(EMPTY_TABLE.input());

        assertThat(def.tables().size(), not(0));
        assertThat(def.tables().get(0).name(), is("table_name"));
        assertThat(def.tables().get(0).columns().size(), is(0));
    }

    @Test
    public void parseSingleColTable() {
        SchemaDef def = new SchemaParser().parse(SINGLE_COL_TABLE.input());
        new SchemaParser().printTree(SINGLE_COL_TABLE.input());

        assertThat(def.tables().size(), is(1));
        assertThat(def.tables().get(0).columns().size(), is(1));
    }

    @Test
    public void parseTwoColTable() {
        SchemaDef def = new SchemaParser().parse(TWO_COL_TABLE.input());
        new SchemaParser().printTree(TWO_COL_TABLE.input());

        assertThat(def.tables().size(), is(1));
        assertThat(def.tables().get(0).columns().size(), is(2));
    }
}
