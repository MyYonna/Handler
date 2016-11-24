package adaptor;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Turky t = new ReallyTurky();
		t.walk();
		Duck  ta = new TurkyAdaptor(t);
		ta.quack();
		ta.fly();
	}

}
