package decorate;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Beverage beverage = new Espresso();
		beverage.setSize(1.0);
		beverage = new Mocha(beverage);
		beverage = new Whip(beverage);
		beverage = new Soy(beverage);
		System.out.println(beverage.getDescription()+"µÄcost:" +beverage.cost());
	}

}
