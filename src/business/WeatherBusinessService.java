package business;

import java.util.List;

import data.WeatherDataService;
import models.Weather;

/**
 * 
 * @author Josh Van de Walle and Austin Harvey
 * The WeatherBusinessService handles business logic and enforces business rules for weather
 *
 */
public class WeatherBusinessService 
{
	// The DAO object that handles database operations
	WeatherDataService service;
	
	public int store(Weather record)
	{
		service = new WeatherDataService();
		return service.create(record);
	}
	
	public List<Weather> search(Weather target)
	{
		service = new WeatherDataService();
		return service.findByDate(target);
	}
}
