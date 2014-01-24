package db.io.operations;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import db.io.UnitTests;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

@Category(UnitTests.class)
public class ValueFactoryTest {
    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    interface SomeData {
        int count();
    }

    @Test
    public void verify_int_value() {
        DataSet ds = new DataSet();
        ds.put(0, new Column(Integer.class, "count", 25));

        ValueFactory<SomeData> factory = new ValueFactory(SomeData.class, ds);


        SomeData data = factory.newRow(0, ds);

        assertThat(data.count(), is(25));

    }
}
