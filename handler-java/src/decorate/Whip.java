package decorate;

public class Whip extends Beverage {
	Beverage beverage;

	public Whip(Beverage beverage) {
		// TODO Auto-generated constructor stub
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return  beverage.getDescription()+"¼ÓÒ»·İ"+"Whip";
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return beverage.cost()+0.68;
	}

}
