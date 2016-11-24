package innerobserver;

import java.util.Observable;

public class WeatherData extends Observable{
	private float temperature;
	private float humidity;
	private float pressure;

	public WeatherData() {
		// TODO Auto-generated constructor stub
	}

	public void measurementsChanged(){
		setChanged();
		float[] obj = new float[3];
		obj[0] = temperature;
		obj[1] = humidity;
		obj[2] = pressure;
		notifyObservers(obj);
	}


	public void setMeasurements(float temperature,float humidity,float pressure){
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}

	
}
