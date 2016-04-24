package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class ReadInteraction1 extends TestJPF {

	static String[] JPF_CONFIGURATION = new String[] { "+interaction=readInteraction",
			"+search.class=.search.RandomSearch", "+choice=MapChoice" };

	@Conditional
	static boolean config_a = true;
	@Conditional
	static boolean config_b = true;

	@Test
	public void readInteraction1() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			TestClass1 globalObj = new TestClass1();
			if (config_a) {
				globalObj.setU(4);
			}

			if (config_b) {
				globalObj.setU(8);
			}

			if (config_a) {
				System.out.println(globalObj.u);
			}
		}
	}
}

class TestClass1 {
	int u;

	public TestClass1() {
		this.u = 1;
	}

	public void setU(int u) {
		this.u = u;
	}
}
