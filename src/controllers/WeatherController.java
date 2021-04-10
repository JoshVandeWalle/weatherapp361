package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import business.WeatherBusinessService;
import models.LineChartBean;
import models.Weather;
import util.RestDto;

/**
 * 
 * @author Josh Van de Walle and Austin Harvey
 * The WeatherController manages behavior for weather
 *
 */

@Named
@ViewScoped
@Path("/weather")
@Interceptors(util.LoggingInterceptor.class) 
public class WeatherController implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// The Business layer facade that abstracts data logic
	WeatherBusinessService service;
	

	/**
	 * This method manages behavior related to posting data in the database
	 * @param record the data to store
	 * @return th
	 */
	@POST
	@Path("/store")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RestDto<Weather> handleStore(Weather record)
	{
		try
		{
			service = new WeatherBusinessService();
			if (service.store(record) == 1) 
			{
				List<Weather> data = new ArrayList<Weather>();
				// confirm the data was stored
				return new RestDto<Weather>(200, "OK", data);
			}
			
			else 
			{
				return new RestDto<Weather>(400, "Bad request", null);
			}
		}
		
		catch (Exception e)
		{
			return new RestDto<Weather>(500, "Internal error", null);
		}
	}
	
	/**
	 * This method manages behavior related to searching for weather data
	 * @param target the target of the search
	 * @return the data found
	 */
	public String handleSearch(Weather target)
	{
		try 
		{
			service = new WeatherBusinessService();
			target.setDatetime(target.getDatetime().replace("-", "/"));
			System.out.println(target.getDatetime());
			List<Weather> result = service.search(target);
			
			if (result.size() > 0)
			{
				LineChartBean lineChartBean = new LineChartBean(result);
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("result", result);
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("lineChartBean", lineChartBean);
				return "Result.xhtml";
			}
			
			else 
			{
				return "Result.xhtml";
			}
		}
		
		catch (Exception e)
		{
			return "Error.xhtml";
		}
	}
}
