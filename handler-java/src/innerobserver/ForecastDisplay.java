package innerobserver;

import java.util.Observable;
import java.util.Observer;

public class ForecastDisplay implements Observer ,DisplayElement{

	private float temp;
	private float humidity;
	private float pressure;
	public ForecastDisplay(Observable o) {
		// TODO Auto-generated constructor stub
		o.addObserver(this);
	}


	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("δ���¶ȣ�"+temp);
		System.out.println("δ��ʪ�ȣ�"+humidity);
		System.out.println("δ����ѹ��"+pressure);
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		float[] data = (float[])arg;
		this.temp = data[0];
		this.humidity = data[1];
		this.pressure = data[2];
		display();
		
		
	}

}
