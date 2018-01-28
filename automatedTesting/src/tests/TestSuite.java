package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ApiTests.class,
        LogicTests.class,
        ClassTests.class
})

public class TestSuite {

}
