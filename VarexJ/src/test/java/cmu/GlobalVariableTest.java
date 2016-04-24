package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class GlobalVariableTest extends TestJPF {

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
	public void globalVariableTest() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			MyClass globalObj = new MyClass();
			if (a) {
				globalObj.setX(5);
			}

			if (b) {
				globalObj.setX(10);
			}

			if (c) {
				globalObj.setX(15);
			}

			System.out.println(globalObj.x);

		}
	}

}

class MyClass {
	int x;

	public MyClass() {
		this.x = 1;
	}

	public void setX(int x) {
		this.x = x;
	}
}
