package db.io;

import db.io.operations.ColFactoryTest;
import db.io.operations.DataSetTest;
import db.io.operations.QueryTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//
// GAH! This is cumbersome!
//
@RunWith(Categories.class)
@Categories.IncludeCategory(UnitTests.class)
@Suite.SuiteClasses( {
        ColFactoryTest.class,
        DataSetTest.class,
        QueryTest.class
})
public class FastRunningTestSuite {
}
