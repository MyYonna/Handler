
public class Test {

	public static void main(String[] args){
		Dunk gd = new GreenDunk();
		Dunk rd = new RedDunk();
		gd.display();
		CanFlyDunk fd = new FlyDunk(rd);
		rd.display();
		fd.fly();;
	}
}
