package decorate;

//װ���ߺͱ�װ���ߵĻ���
public abstract class Beverage {

	public Beverage() {
		// TODO Auto-generated constructor stub
	}

	String description = "Unknow Beverage";
	
	public String getDescription(){
		return description;
	}
	
	public abstract double cost();
	
	public double size;

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
