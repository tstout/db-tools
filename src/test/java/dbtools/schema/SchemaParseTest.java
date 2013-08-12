package dbtools.schema;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SchemaParseTest {
    @Test
    public void parseEmptyTable() {
//        SyntaxErrorListener errors = new SyntaxErrorListener();
//
//        ParseTree tree = new SchemaParserBuilder()
//                .withDef(" table name ()")
//                .withErrListener(errors)
//                .build();
//
//        errors.printErrors();


        SchemaDef def = new SchemaParser().parse("table name ()");
        assertThat(def.tables().size(), not(0));
    }
}
