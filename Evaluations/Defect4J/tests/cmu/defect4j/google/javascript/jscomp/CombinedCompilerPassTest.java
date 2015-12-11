package cmu.defect4j.google.javascript.jscomp;

import gov.nasa.jpf.util.test.TestJPF;
import org.junit.Test;
import junit.framework.TestCase;
public class CombinedCompilerPassTest extends TestJPF {

    private final String[] config = {"+nhandler.delegateUnhandledNative", "+classpath+=${jpf-core}/lib/junit-4.11.jar,lib/compiler.jar"};

    public static void main(String[] testMethods){
        runTestsOfThisClass(testMethods);
    }
	@Test(timeout=120000)
	public void testIndividualPasses() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new com.google.javascript.jscomp.CombinedCompilerPassTest() {
				public void runTest() throws Exception {
					testIndividualPasses();
				}
			};
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testCombinedPasses() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new com.google.javascript.jscomp.CombinedCompilerPassTest() {
				public void runTest() throws Exception {
					testCombinedPasses();
				}
			};
			testcase.run();
		}
	}

	@Test(timeout=120000)
	public void testScopes() throws Exception {
		if (verifyNoPropertyViolation(config)) {
			TestCase testcase = new com.google.javascript.jscomp.CombinedCompilerPassTest() {
				public void runTest() throws Exception {
					testScopes();
				}
			};
			testcase.run();
		}
	}

}