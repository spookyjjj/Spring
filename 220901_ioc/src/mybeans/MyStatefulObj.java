package mybeans;

public class MyStatefulObj {
	private String name;
	private int number;
	
	public MyStatefulObj() {}

	public MyStatefulObj(String name, int number) {
		super();
		this.name = name;
		this.number = number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "MyStatefulObj [name=" + name + ", number=" + number + "]";
	}
	
	
}
