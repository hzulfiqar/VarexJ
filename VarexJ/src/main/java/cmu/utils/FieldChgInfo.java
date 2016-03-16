package cmu.utils;

import de.fosd.typechef.featureexpr.FeatureExpr;

public class FieldChgInfo {

	private FeatureExpr ctx;
	private int lineNumber;
	private String className;

	public FieldChgInfo(FeatureExpr ctx, int lineNumber, String className) {
		this.ctx = ctx;
		this.lineNumber = lineNumber;
		this.className = className;
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
}
