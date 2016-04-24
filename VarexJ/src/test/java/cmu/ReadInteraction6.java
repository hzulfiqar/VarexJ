package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class ReadInteraction6 extends TestJPF {

	static String[] JPF_CONFIGURATION = new String[] { "+interaction=writeInteraction",
			"+search.class=.search.RandomSearch", "+choice=MapChoice" };

	@Conditional
	static boolean config_a = true;
	@Conditional
	static boolean config_b = true;

	@Test
	public void readInteraction6() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			TestClass6 globalObj = new TestClass6();
			if (config_a) {
				globalObj.setU(4);
			}

			if (config_b) {
				globalObj.incrementU(8);
			}

			if (!config_a) {
				System.out.println(globalObj.u);
			}
		}
	}
}

class TestClass6 {
	int u;

	public TestClass6() {
		this.u = 1;
	}

	public void incrementU(int inc) {
		u += inc;
	}

	public void setU(int u) {
		this.u = u;
	}
}