package db.io.operations;

import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import db.io.UnitTests;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;

import java.util.Collection;

import static com.google.common.collect.FluentIterable.from;
import static org.hamcrest.CoreMatchers.not;
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


        Collection<SomeData> data = factory.create(ds);

        assertThat(data.size(), not(0));
        assertThat(from(data).first().get().count(), is(25));

    }
}
