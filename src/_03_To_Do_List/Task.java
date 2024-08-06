package _03_To_Do_List;

public class Task {

	private String description;
	private int order;
	
	public Task(int order, String description) {
		this.description = description;
		this.order = order;
	}
	
	
	
	
	public void setDesc(String description) {
		this.description = description;
	}
	public String desc() {
		return description;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int order() {
		return order;
	}
}
