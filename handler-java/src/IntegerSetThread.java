
public class IntegerSetThread implements Runnable {

	public MutableInteger mi;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		mi.setName("��");
	}
	public MutableInteger getMi() {
		return mi;
	}
	public void setMi(MutableInteger mi) {
		this.mi = mi;
	}

}
