package adaptor;

public class TurkyAdaptor implements Duck {

	Turky turky;
	
	public TurkyAdaptor(Turky turky) {
		// TODO Auto-generated constructor stub
		this.turky = turky;
	}

	@Override
	public void quack() {
		// TODO Auto-generated method stub
		turky.quack();

	}

	@Override
	public void fly() {
		// TODO Auto-generated method stub

		turky.walk();
		turky.walk();
		turky.walk();
		turky.walk();
		turky.walk();
		System.out.println("我已经走了五米了");
	}

}
