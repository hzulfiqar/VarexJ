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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result + objectRef;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectInfo other = (ObjectInfo) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (objectRef != other.objectRef)
			return false;
		return true;
	}
	
	
	
	
}