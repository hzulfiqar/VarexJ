package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class MutuallyExclusiveTest2 extends TestJPF {

	static String[] JPF_CONFIGURATION = new String[] { "+interaction=writeInteraction",
			"+search.class=.search.RandomSearch", "+choice=MapChoice" };

	@Conditional
	static boolean config_a = true;
	@Conditional
	static boolean config_b = true;
	@Conditional
	static boolean config_c = true;

	@Test
	public void mutuallyExclusiveTest2() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			Class2 globalObj = new Class2();
			if (config_a) {
				globalObj.setS(3);
			}
			if (config_b) {
				globalObj.setS(13);
			}
			if (!config_a && !config_b && config_c) {
				globalObj.setS(30);
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