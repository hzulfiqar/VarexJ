package cmu.utils;

import de.fosd.typechef.featureexpr.FeatureExpr;

public class FieldChgInfo {

	private FeatureExpr ctx;
	private int lineNumber;

	public FieldChgInfo(FeatureExpr ctx, int lineNumber) {
		this.ctx = ctx;
		this.lineNumber = lineNumber;
	}

	public FeatureExpr getCtx() {
		return this.ctx;
	}

	public int getLineNumber() {
		return this.lineNumber;
	}
}
