package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class MutuallyExclusiveTest extends TestJPF {

	static String[] JPF_CONFIGURATION = new String[] { "+interaction=writeInteraction",
			"+search.class=.search.RandomSearch", "+choice=MapChoice" };

	@Conditional
	static boolean config_a = true;
	@Conditional
	static boolean config_b = true;

	@Test
	public void mutuallyExclusiveTest() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			MyClass2 globalObj = new MyClass2();
			if (config_a) {
				globalObj.setW(7);
			}
			if (!config_a) {
				globalObj.setW(80);
			}

			System.out.println(globalObj.w);

		}
	}

}

class MyClass2 {
	int w;

	public MyClass2() {
		this.w = 2;
	}

	public void setW(int w) {
		this.w = w;
	}
}
