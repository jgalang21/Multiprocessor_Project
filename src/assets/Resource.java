package assets;

public class Resource {
	private String Resource;
	private int value;
	private boolean locked = false;
	
	public Resource (String Resource, int value) {
		this.Resource = Resource;
		this.value = value;
	}
	
	public String get_Shared_Resource() {
		return Resource;
	}
	
	public int get_Shared_Value() {
		return value;
	}
	
	public String get_Exclusive_Resource() {
		return Resource;
	}
	public int get_Exclusive_Value() {
		return value;
	}
	
	/**
	 * New boolean getters and setters below
	 * 
	 * We could change the name of it to "Shared/exclusive" or something like that,
	 * whatever works for you.
	 */
	public boolean isLocked() {
		return locked;
	}
	
	public boolean unlock() {
		locked = true;
		return locked;
	}
	
	public boolean lock() {
		locked = false;
		return locked;
	}
	
	
}
