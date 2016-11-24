package observer;

public class CurrentDisplay implements Observer ,DisplayElement{

	private float temp;
	private float humidity;
	private float pressure;
	private Observer o;
	public CurrentDisplay(Subject s) {
		// TODO Auto-generated constructor stub
		s.registerObserver(this);
	}

	@Override
	public void update(float temp, float humidity, float pressure) {
		// TODO Auto-generated method stub
		this.temp  = temp;
		this.humidity  = humidity;
		this.pressure  = pressure;
		display();
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("��ǰ�¶ȣ�"+temp);
		System.out.println("��ǰʪ�ȣ�"+humidity);
		System.out.println("��ǰ��ѹ��"+pressure);
	}

}
