package assets;

public class Resource {
	private String Resource;
	private int value;
	
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
}
