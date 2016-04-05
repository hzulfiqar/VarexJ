package cmu.utils;

import cmu.conditional.Conditional;
import de.fosd.typechef.featureexpr.FeatureExpr;

public class FieldChgInfo {

	private FeatureExpr ctx;
	private int lineNumber;
	private String className;
	private Conditional<?> fieldValue;

	public FieldChgInfo(FeatureExpr ctx, int lineNumber, String className, Conditional<?> fieldValue) {
		this.ctx = ctx;
		this.lineNumber = lineNumber;
		this.className = className;
		this.fieldValue = fieldValue;
	}

	public FeatureExpr getCtx() {
		return this.ctx;
	}

	public int getLineNumber() {
		return this.lineNumber;
	}

	public String getClassName() {
		return this.className;
	}
	
	public Conditional<?> getFieldValue(){
		return this.fieldValue;
	}
}
