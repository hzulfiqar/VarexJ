package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class DifferentReadWriteCtxTest extends TestJPF {
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
	public void differentReadWriteCtx() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			MyClass1 classObj = new MyClass1();
			if (a) {
				System.out.println(classObj.v);
			}
		}
	}
}

class MyClass1 {
	int v;

	public MyClass1() {
		this.v = 3;
	}

	public void setV(int v) {
		this.v = v;
	}

}