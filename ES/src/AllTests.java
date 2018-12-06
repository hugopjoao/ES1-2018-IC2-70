import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(FacebookTest.class);
		suite.addTestSuite(MailTest.class);
		suite.addTestSuite(TwitterTest.class);
		//$JUnit-END$
		return suite;
	}

}
