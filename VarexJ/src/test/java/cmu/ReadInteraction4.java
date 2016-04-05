package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class ReadInteraction4 extends TestJPF {

	static String[] JPF_CONFIGURATION = new String[] { "+interaction=readInteraction",
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
	public void readInteraction4() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			TestClass4 globalObj = new TestClass4();
			if (a) {
				globalObj.setU(4);
			}

			if (b) {
				globalObj.incrementU(8);
			}

			if (b) {
				System.out.println(globalObj.u);
			}
		}
	}
}

class TestClass4 {
	int u;

	public TestClass4() {
		this.u = 1;
	}

	public void incrementU(int inc) {
		u += inc;
	}

	public void setU(int u) {
		this.u = u;
	}
}