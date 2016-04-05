package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class MutuallyExclusiveTest2 extends TestJPF {

	static String[] JPF_CONFIGURATION = new String[] { "+interaction=readInteraction",
			"+search.class=.search.RandomSearch", "+choice=MapChoice" };

	@Conditional
	static boolean a = true;
	@Conditional
	static boolean b = true;
	@Conditional
	static boolean c = true;

	@Test
	public void mutuallyExclusiveTest2() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			Class2 globalObj = new Class2();
			if (a) {
				globalObj.setS(3);
			}
			if (b) {
				globalObj.setS(13);
			}
			if (!a && !b && c) {
				globalObj.setS(30);
			}

			if (c) {
				globalObj.setS(35);
			}

			System.out.println(globalObj.s);
		}
	}

}

class Class2 {
	int s;

	public Class2() {
		this.s = 1;
	}

	public void setS(int s) {
		this.s = s;
	}
}