package decorate;

public class Mocha extends Beverage {

	Beverage beverage;
	public Mocha(Beverage beverage) {
		// TODO Auto-generated constructor stub
		this.beverage = beverage;
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return beverage.cost()+0.14;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return beverage.getDescription()+"¼ÓÒ»·İ"+"Mocha";
	}

}
