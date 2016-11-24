package innerobserver;

import java.util.Observer;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args){
		WeatherData s = new WeatherData();
		Observer cd = new CurrentDisplay(s);
		Observer fd = new ForecastDisplay(s);
		s.setMeasurements(100.0f, 200.0f,1.9f);
	}
}
