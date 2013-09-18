package dbtools.schema;

import org.junit.Test;

import java.util.List;

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

        assertThat(def.numTables(), is(1));
        assertThat(def.table("table_x").numCols(), not(0));
    }

    @Test
    public void parseMultipleColTable() {
        SchemaDef def = new SchemaParser().parse(MULTI_COL_TABLE.input());
        new SchemaParser().printTree(MULTI_COL_TABLE.input());

        assertThat(def.numTables(), not(0));
        assertThat(def.table("table_x").numCols(), is(4));
    }

    @Test
    public void parseColAttributes() {
        SchemaDef def = new SchemaParser().parse(PK_COL_TABLE.input());
        new SchemaParser().printTree(PK_COL_TABLE.input());

        assertThat(def.numTables(), is(1));

        List<ColAttribute> colAttributes =
            def.table("table_y").col("id").get().attributes();

        assertThat(colAttributes.size(), not(0));
        assertThat(colAttributes.get(0), is(ColAttribute.PK));
    }

}
