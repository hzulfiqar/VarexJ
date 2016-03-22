package cmu;

import org.junit.Test;

import gov.nasa.jpf.annotation.Conditional;
import gov.nasa.jpf.util.test.TestJPF;

public class SeparateObjectsTest extends TestJPF {

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
	public void separateObjectsTest() {
		if (verifyNoPropertyViolation(JPF_CONFIGURATION)) {
			Class1 obj1 = new Class1();

			if (a) {
				Class1 obj2 = new Class1();
				obj2.setZ(10);
			}

			if (b) {
				Class1 obj3 = new Class1();
				obj3.setZ(20);
				System.out.println(obj3.z);
			}

			if (c) {
				Class1 obj4 = new Class1();
				obj4.setZ(20);
			}

			System.out.println(obj1.z);

		}
	}

}

class Class1 {
	int z;

	public Class1() {
		this.z = 2;
	}

	public void setZ(int z) {
		this.z = z;
	}
}