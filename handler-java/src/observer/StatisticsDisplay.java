package observer;

public class StatisticsDisplay implements Observer,DisplayElement {

	public StatisticsDisplay() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float temp, float humidity, float pressure) {
		// TODO Auto-generated method stub
		System.out.println("打印统计天气信息");
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

}
