//
// Copyright (C) 2006 United States Government as represented by the
// Administrator of the National Aeronautics and Space Administration
// (NASA).  All Rights Reserved.
// 
// This software is distributed under the NASA Open Source Agreement
// (NOSA), version 1.3.  The NOSA has been approved by the Open Source
// Initiative.  See the file NOSA-1.3-JPF at the top of the distribution
// directory tree for the complete NOSA document.
// 
// THE SUBJECT SOFTWARE IS PROVIDED "AS IS" WITHOUT ANY WARRANTY OF ANY
// KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT
// LIMITED TO, ANY WARRANTY THAT THE SUBJECT SOFTWARE WILL CONFORM TO
// SPECIFICATIONS, ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
// A PARTICULAR PURPOSE, OR FREEDOM FROM INFRINGEMENT, ANY WARRANTY THAT
// THE SUBJECT SOFTWARE WILL BE ERROR FREE, OR ANY WARRANTY THAT
// DOCUMENTATION, IF PROVIDED, WILL CONFORM TO THE SUBJECT SOFTWARE.
//
package gov.nasa.jpf.jvm.bytecode;

import gov.nasa.jpf.vm.StackFrame;
import cmu.conditional.Conditional;
import de.fosd.typechef.featureexpr.FeatureExpr;

/**
 * Branch if int comparison succeeds ..., value1, value2 => ...
 */
public class IF_ICMPLT extends IfInstruction {

	public IF_ICMPLT(int targetPc) {
		super(targetPc);
	}

	public Conditional<Boolean> popConditionValue(FeatureExpr ctx, StackFrame frame) {
		final Conditional<Integer> v1 = frame.pop(ctx);
		final Conditional<Integer> v2 = frame.pop(ctx);
		return maprIf(v1, v2);
	}

	@Override
	protected boolean compare(int x, int y) {
		return x > y;
	}

	public int getByteCode() {
		return 0xA1;
	}

	public void accept(InstructionVisitor insVisitor) {
		insVisitor.visit(this);
	}
}
