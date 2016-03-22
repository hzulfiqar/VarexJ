package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class PreviousSeenCtxTest extends TestJPF {

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
	public void globalVariableTest() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			Class3 globalObj = new Class3();
			if (a) {
				globalObj.setU(4);
			}

			if (b) {
				globalObj.setU(4);
			}

			if (a) {
				System.out.println(globalObj.u);
			}
		}
	}

}

class Class3 {
	int u;

	public Class3() {
		this.u = 1;
	}

	public void setU(int u) {
		this.u = u;
	}
}