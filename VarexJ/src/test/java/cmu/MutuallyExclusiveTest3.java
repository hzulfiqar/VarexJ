package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class MutuallyExclusiveTest3 extends TestJPF {

	static String[] JPF_CONFIGURATION = new String[] { "+interaction=readInteraction",
			"+search.class=.search.RandomSearch", "+choice=MapChoice" };

	@Conditional
	static boolean a = true;
	@Conditional
	static boolean b = true;
	@Conditional
	static boolean c = true;

	@Test
	public void mutuallyExclusiveTest3() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			MXClass globalObj = new MXClass();
			if (a) {
				globalObj.setS(33);
			}
			if (!a) {
				globalObj.setS(30);
			}
			if (a && b) {
				globalObj.setS(39);
			}

			if (!a && b) {
				globalObj.setS(300);
			}

			System.out.println(globalObj.s);
		}
	}

}

class MXClass {
	int s;

	public MXClass() {
		this.s = 1;
	}

	public void setS(int s) {
		this.s = s;
	}
}