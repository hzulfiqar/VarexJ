package cmu.utils;

public class ObjectInfo{
	String className;
	int objectRef;
			
	public ObjectInfo(String className, int objectRef) {
		this.className = className;
		this.objectRef = objectRef;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getObjectRef() {
		return objectRef;
	}
	public void setObjectRef(int objectRef) {
		this.objectRef = objectRef;
	}
	
	
}