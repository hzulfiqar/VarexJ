package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class ReadInteraction5 extends TestJPF {

	static String[] JPF_CONFIGURATION = new String[] { "+interaction=readInteraction",
			"+search.class=.search.RandomSearch", "+choice=MapChoice" };

	@Conditional
	static boolean a = true;
	@Conditional
	static boolean b = true;

	@Test
	public void readInteraction5() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			TestClass5 globalObj = new TestClass5();
			if (b) {
				globalObj.setU(4);
			}

			if (b) {
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