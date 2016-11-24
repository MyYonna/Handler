
public class IntegerGetThread implements Runnable {

	public MutableInteger mi;
	@Override
	public void run() {
		// TODO Auto-generated method stub

		System.out.println(mi.getName());
	}
	public MutableInteger getMi() {
		return mi;
	}
	public void setMi(MutableInteger mi) {
		this.mi = mi;
	}

}
