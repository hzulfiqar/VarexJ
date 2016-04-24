package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class ReadInteraction2 extends TestJPF {

	static String[] JPF_CONFIGURATION = new String[] { "+interaction=readInteraction",
			"+search.class=.search.RandomSearch", "+choice=MapChoice" };

	@Conditional
	static boolean config_a = true;
	@Conditional
	static boolean config_b = true;

	@Test
	public void readInteraction2() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			TestClass2 globalObj = new TestClass2();
			if (config_a) {
				globalObj.setU(8);
			}

			if (config_b) {
				globalObj.setU(40);
			}

			if (config_b) {
				System.out.println(globalObj.u);
			}
		}
	}
}

class TestClass2 {
	int u;

	public TestClass2() {
		this.u = 2;
	}

	public void setU(int u) {
		this.u = u;
	}
}
