package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class ReadInteraction3 extends TestJPF {
	static String[] JPF_CONFIGURATION = new String[] { "+interaction=writeInteraction",
			"+search.class=.search.RandomSearch", "+choice=MapChoice" };

	@Conditional
	static boolean a = true;
	@Conditional
	static boolean b = true;
	@Conditional
	static boolean c = true;
	@Conditional
	static boolean d = true;

	@Test
	public void readInteraction3() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			TestClass3 globalObj = new TestClass3();
			if (a) {
				globalObj.setU(4);
			}

			if (b) {
				globalObj.incrementU(8);
			}

			if (a) {
				System.out.println(globalObj.u);
			}
		}
	}
}

class TestClass3 {
	int u;

	public TestClass3() {
		this.u = 1;
	}

	public void incrementU(int inc) {
		u += inc;
	}

	public void setU(int u) {
		this.u = u;
	}
}
