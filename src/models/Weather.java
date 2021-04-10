package models;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;

import org.primefaces.model.chart.LineChartModel;

/**
 * This class is an object model representation of weather data
 * @author t34su
 *
 */
@ManagedBean
@ViewScoped
public class Weather 
{
	
	// the unique ID
	private int id;
	
	// the temperature (farenheit)
	private int temp;
	
	// the humidity 
	private int humidity;
	
	// the precipitation 
	private int pressure;
	
	// the timestamp for this weather
	@NotNull()
	private String datetime;
	
	// the model to create a graph from
	private LineChartModel lineModel;
	

	public Weather() {
		super();
	}



	/**
	 * 
	 * @param id the unique ID
	 * @param temp the temperature (farenheit)
	 * @param humidity the humidity 
	 * @param precipitation the precipitation 
	 * @param datetime the timestamp for this weather
	 */
	public Weather(int id, int temp, int humidity, int pressure, String datetime) 
	{
		super();
		this.id = id;
		this.temp = temp;
		this.humidity = humidity;
		this.pressure = pressure;
		this.datetime = datetime;
	}



	public int getId() {
		return id;
	}



	public int getTemp() {
		return temp;
	}



	public int getHumidity() {
		return humidity;
	}



	public int getPressure() {
		return pressure;
	}



	public String getDatetime() {
		return datetime;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setTemp(int temp) {
		this.temp = temp;
	}



	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}



	public void setPressure(int pressure) {
		this.pressure = pressure;
	}



	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
}
