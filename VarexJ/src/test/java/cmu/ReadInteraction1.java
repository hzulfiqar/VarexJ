package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class ReadInteraction1 extends TestJPF {

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
	public void readInteraction1() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			TestClass1 globalObj = new TestClass1();
			if (a) {
				globalObj.setU(4);
			}

			if (b) {
				globalObj.setU(8);
			}

			if (a) {
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
