package innerobserver;

import java.util.Observable;
import java.util.Observer;

public class CurrentDisplay implements Observer ,DisplayElement{

	private float temp;
	private float humidity;
	private float pressure;
	public CurrentDisplay(Observable s) {
		// TODO Auto-generated constructor stub
		s.addObserver(this);
	}


	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("��ǰ�¶ȣ�"+temp);
		System.out.println("��ǰʪ�ȣ�"+humidity);
		System.out.println("��ǰ��ѹ��"+pressure);
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
