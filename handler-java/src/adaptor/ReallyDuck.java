package adaptor;

public class ReallyDuck implements Duck {

	public ReallyDuck() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void quack() {
		// TODO Auto-generated method stub

		System.out.println("呱呱呱");
	}

	@Override
	public void fly() {
		// TODO Auto-generated method stub
		System.out.println("我能飞五米远");
	}

}
