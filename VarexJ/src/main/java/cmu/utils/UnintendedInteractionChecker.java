package cmu.utils;

import gov.nasa.jpf.vm.FieldInfo;
import gov.nasa.jpf.vm.StackFrame;
import gov.nasa.jpf.vm.ThreadInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cmu.conditional.Conditional;
import cmu.conditional.One;
import de.fosd.typechef.featureexpr.FeatureExpr;
import de.fosd.typechef.featureexpr.FeatureExprFactory;

public class UnintendedInteractionChecker {

	private static Map<ObjectInfo, Map<Integer, List<FieldChgInfo>>> objectCtxChangeMap = new HashMap<ObjectInfo, Map<Integer, List<FieldChgInfo>>>();

	public static void checkUnintendedWriteInteraction(Conditional<Integer> field, FeatureExpr ctx, ThreadInfo ti,
			StackFrame frame, int objectRef, FieldInfo fi, Conditional<Integer> val) {
		FeatureExpr prevCtx = null;
		Map<Integer, List<FieldChgInfo>> fieldCtxChangeMap = null;
		List<FieldChgInfo> fieldChgInfoList = null;

		ObjectInfo objectInfo = new ObjectInfo(frame.getClassInfo().getName(), objectRef);

		// if (val instanceof One<?>) {
		// System.out.println("Concrete value: " + val.getValue());
		// } else {
		// System.out.println("Choice: " + val.toMap());
		// System.out.println("Choice under ctx: " + ctx + " " +
		// val.simplify(ctx));
		// }

		// check if we saw this object before (both class name and object ref
		// must be unique)
		if (objectCtxChangeMap.containsKey(objectInfo)) {
			fieldCtxChangeMap = objectCtxChangeMap.get(objectInfo);
			fieldChgInfoList = fieldCtxChangeMap.get(fi.getFieldIndex());

			if (fieldChgInfoList != null) {
				// we saw this field before and can get the last context under
				// which it was modified
				FieldChgInfo latestFieldChgInfo = fieldChgInfoList.get(fieldChgInfoList.size() - 1);
				prevCtx = latestFieldChgInfo.getCtx();
			} else {
				// we did not see this field before so we will create a new List
				// for it
				fieldChgInfoList = new ArrayList<FieldChgInfo>();
			}
		} else {
			// we did not see this object before so we will leave prevCtx as
			// null
			// and add the current field info into a new list and corresponding
			// map
			fieldChgInfoList = new ArrayList<FieldChgInfo>();
			fieldCtxChangeMap = new HashMap<Integer, List<FieldChgInfo>>();

			// TODO: Why do we have to get the previous frame to get the correct
			// line number ?!
			// if (!ctx.equivalentTo(FeatureExprFactory.True())) {
			if (fi.isStatic()) {
				fieldChgInfoList.add(new FieldChgInfo(ctx, frame.getPC().simplify(ctx).getValue().getLineNumber(),
						frame.getClassInfo().getName(), val.simplify(ctx)));
			} else {
				fieldChgInfoList
						.add(new FieldChgInfo(ctx, frame.getPrevious().getPC().simplify(ctx).getValue().getLineNumber(),
								frame.getPrevious().getClassInfo().getName(), val.simplify(ctx)));
			}
			fieldCtxChangeMap.put(fi.getFieldIndex(), fieldChgInfoList);
			objectCtxChangeMap.put(objectInfo, fieldCtxChangeMap);
			// }
		}

		if (prevCtx != null && !ctx.equivalentTo(prevCtx)) {
			// System.out.println(ctx + " " + prevCtx + " " +
			// ctx.and(prevCtx).isContradiction());
			FieldChgInfo currentFieldChgInfo = null;
			// TODO: Why do we use current frame for static fields but previous
			// frame for non-static ones?!
			if (fi.isStatic()) {
				currentFieldChgInfo = new FieldChgInfo(ctx, frame.getPC().simplify(ctx).getValue().getLineNumber(),
						frame.getClassInfo().getName(), val.simplify(ctx));

			} else {
				currentFieldChgInfo = new FieldChgInfo(ctx,
						frame.getPrevious().getPC().simplify(ctx).getValue().getLineNumber(),
						frame.getPrevious().getClassInfo().getName(), val.simplify(ctx));
			}

			fieldChgInfoList.add(currentFieldChgInfo);
			fieldCtxChangeMap.put(fi.getFieldIndex(), fieldChgInfoList);
			objectCtxChangeMap.put(objectInfo, fieldCtxChangeMap);

//			if (!ctx.and(prevCtx).isContradiction()) {
				ti.coverage.coverWriteField(ctx, val, field, fi, objectCtxChangeMap, frame, objectInfo);
				// }
//			}
		}
	}

	public static void checkUnintendedReadInteraction(StackFrame frame, FieldInfo fi, int objectRef, FeatureExpr ctx,
			ThreadInfo ti, Conditional<Integer> val, Conditional<Integer> ival) {

		ObjectInfo objectInfo = new ObjectInfo(fi.getClassInfo().getName(), objectRef);
		Map<Integer, List<FieldChgInfo>> fieldInfoMap = objectCtxChangeMap.get(objectInfo);
		if (fieldInfoMap != null) {
			List<FieldChgInfo> fieldChgInfoList = fieldInfoMap.get(fi.getFieldIndex());
			if (fieldChgInfoList != null) {
				// for (int i = 0; i < fieldChgInfoList.size(); i++) {
				// FieldChgInfo fieldChgInfoObj = fieldChgInfoList.get(i);
				// if (fieldChgInfoObj.getCtx().equivalentTo(ctx)) {
				// // System.out.println("In list " +
				// // fieldChgInfoObj.getCtx() + " value: "
				// // + fieldChgInfoObj.getFieldValue() + " ,Current: " +
				// // ctx + " " + ival);
				// }
				// }

				FieldChgInfo lastInfoObj = fieldChgInfoList.get(fieldChgInfoList.size() - 1);
				FeatureExpr prevCtx = lastInfoObj.getCtx();
				// System.out.println("Simple: " + ival);
				// System.out.println("Under: " + Conditional.getCTXString(ctx)
				// + " " + ival.simplify(ctx));
				// if (!prevCtx.equivalentTo(ctx)) {
				if (!(ival.simplify(ctx) instanceof One<?>)) {
					// System.out.println("Not!");
					ti.coverage.coverReadField(ctx, ival, val, prevCtx, fi, frame, objectCtxChangeMap, objectInfo);
				} else {
					// System.out.println("Yes!");
				}

				// }
			}

		}

	}
}
