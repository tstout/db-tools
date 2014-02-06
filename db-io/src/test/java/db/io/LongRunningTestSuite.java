package db.io;

import db.io.operations.H2IntTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(IntegrationTests.class)
@Suite.SuiteClasses( { H2IntTest.class })
public class LongRunningTestSuite {
}
