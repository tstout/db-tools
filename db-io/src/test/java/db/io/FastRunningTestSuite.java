package db.io;

import db.io.config.ConfigPropertiesTest;
import db.io.operations.ArgSetterTest;
import db.io.operations.ColFactoryTest;
import db.io.operations.DataSetTest;
import db.io.operations.QueryTest;
import db.io.operations.ValueFactoryTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(UnitTests.class)
@Suite.SuiteClasses( {
        ColFactoryTest.class,
        DataSetTest.class,
        QueryTest.class,
        ValueFactoryTest.class,
        ConfigPropertiesTest.class,
        ArgSetterTest.class
})
public class FastRunningTestSuite {
}
