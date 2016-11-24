package decorate;

public class Soy extends Condiment {

	Beverage beverage;
	public Soy(Beverage beverage) {
		// TODO Auto-generated constructor stub
		this.beverage = beverage;
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return beverage.cost() + getSize();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "一个大杯的"+beverage.getDescription();
	}

}
