package db.io;

import db.io.operations.QueryIntTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(IntegrationTests.class)
@Suite.SuiteClasses( { QueryIntTest.class })
public class LongRunningTestSuite {
}
