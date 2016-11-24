package observer;

public class ForecastDisplay implements Observer ,DisplayElement{

	private float temp;
	private float humidity;
	private float pressure;
	public ForecastDisplay(Subject s) {
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
		System.out.println("δ���¶ȣ�"+temp);
		System.out.println("δ��ʪ�ȣ�"+humidity);
		System.out.println("δ����ѹ��"+pressure);
	}

}
