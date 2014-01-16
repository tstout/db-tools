package db.io.operations;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class DataSetTest {

    @Test
    public void populateDataTest() {
        DataSet ds = new DataSet();

        ds.put(0, new Column(Integer.class, "ColOne", 1))
          .put(0, new Column(String.class, "ColTwo", "testVal1"))
          .put(1, new Column(Integer.class, "ColOne", 1))
          .put(1, new Column(Integer.class, "ColTwo", "testVal2"));

        assertThat(ds.get(0, "ColOne").val(Integer.class), is(1));
        assertThat(ds.get(1, "ColTwo").val(String.class), is("testVal2"));
    }
}
