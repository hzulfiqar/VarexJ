package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class ReadInteraction5 extends TestJPF {

	static String[] JPF_CONFIGURATION = new String[] { "+interaction=readInteraction",
			"+search.class=.search.RandomSearch", "+choice=MapChoice" };

	@Conditional
	static boolean config_a = true;
	@Conditional
	static boolean config_b = true;

	@Test
	public void readInteraction5() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			TestClass5 globalObj = new TestClass5();
			if (config_b) {
				globalObj.setU(4);
			}

			if (config_b) {
				System.out.println(globalObj.u);
			}
		}
	}
}

class TestClass5 {
	int u;

	public TestClass5() {
		this.u = 1;
	}

	public void setU(int u) {
		this.u = u;
	}
}