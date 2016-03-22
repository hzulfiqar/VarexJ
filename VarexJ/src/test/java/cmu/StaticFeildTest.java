package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class StaticFeildTest extends TestJPF {
	static String[] JPF_CONFIGURATION = new String[] { "+interaction=readInteraction",
			"+search.class=.search.RandomSearch", "+choice=MapChoice" };

	@Conditional
	static boolean a = true;
	@Conditional
	static boolean b = true;
	@Conditional
	static boolean c = true;

	@Test
	public void staticFieldTest() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			if (a) {
				StaticFieldClass.staticField = 6;
			}

			if (b) {
				StaticFieldClass.staticField = 8;
			}

			if (c) {
				StaticFieldClass.staticField = 12;
			}

			System.out.println(StaticFieldClass.staticField);
		}

	}

}

class StaticFieldClass {

	static int staticField = 0;
}