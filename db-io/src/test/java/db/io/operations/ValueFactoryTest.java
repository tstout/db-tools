package db.io.operations;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import db.io.UnitTests;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;

import java.util.Collection;

import static com.google.common.collect.FluentIterable.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

@Category(UnitTests.class)
public class ValueFactoryTest {
    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    interface SomeData {
        int count();
        String descr();
    }

    @Test
    public void verify_basic_proxy_value() {
        DataSet ds = new DataSet();
        Column intCol = new Column(Integer.class, "count", 25);
        Column strCol = new Column(String.class, "descr", "test-val");

        ds.put(0, intCol);
        ds.put(0, strCol);
        ds.put(1, intCol);
        ds.put(1, strCol);

        ValueFactory factory = new ValueFactory(SomeData.class, ds);
        Collection<SomeData> data = factory.create(SomeData.class, ds);

        assertThat(data.size(), is(2));
        assertThat(from(data).first().get().count(), is(25));
        assertThat(from(data).first().get().descr(), is("test-val"));

    }
}
