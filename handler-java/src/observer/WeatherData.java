package observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject{
	private float temperature;
	private float humidity;
	private float pressure;
	private List observers;

	public WeatherData() {
		// TODO Auto-generated constructor stub
		observers = new ArrayList();
	}

	public void measurementsChanged(){
		
		notifyObserver();
	}


	public void setMeasurements(float temperature,float humidity,float pressure){
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}

	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
		
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.remove(o);
		
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		int l = observers.size();
		for(int i=0;i<l;i++){
			Observer o = (Observer)observers.get(i);
			o.update(temperature,humidity, pressure);
		}
	}
		
}
