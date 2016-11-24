
public class FlyDunk extends Dunk  implements CanFlyDunk{

	private Dunk dunk;
	
	public FlyDunk(Dunk dunk) {
		super();
		this.dunk = dunk;
	}

	public Dunk getDunk() {
		return dunk;
	}

	public void setDunk(Dunk dunk) {
		this.dunk = dunk;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		dunk.display();
	}
	@Override
	public void fly(){
		System.out.println("这个只鸭子会飞");
	}

}
