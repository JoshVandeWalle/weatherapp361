package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Weather;

/**
 * DAO object that handles database operations
 * @author Josh Van de Walle and Austin Harvey
 *
 */
public class WeatherDataService 
{
	/**
	 * This method creates a weather record
	 * @param record
	 * @return
	 */
	public int create(Weather record)
	{
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/weatherapp";
		String username = "root";
		String password = "root";
		String sql = "INSERT INTO weatherapp.weather(DATE_TIME, TEMP, PRESS, HUMID) VALUES('" + record.getDatetime() + "'," + record.getTemp() + "," + record.getPressure() + ", " + record.getHumidity() + ")";
		try
		{
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
		finally
		{
			if(conn != null)
			{
				try
				{
					conn.close();
					return 1;
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					return 0;
				}
			}
			
		}
		
		return 0;
    }
	
	/**
	 * This method finds weather data by date
	 * @param record
	 * @return
	 */
	public List<Weather> findByDate(Weather record)
	{
		String url = "jdbc:mysql://localhost:3306/weatherapp";
		String username = "root";
		String password = "root";
		System.out.println("record " + record.getDatetime());
		String sql = "SELECT * FROM `weather` WHERE DATE_TIME LIKE '" + record.getDatetime() + "%'";
		Connection conn = null;
		List<Weather> weatherList = new ArrayList<Weather>();
		
		try
		{
			System.out.println("=====>SHOULD BE OBTAINING RESULT<=====");
			conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				weatherList.add(new Weather(rs.getInt("ID"), rs.getInt("TEMP"), rs.getInt("HUMID"), rs.getInt("PRESS"), rs.getString("DATE_TIME")));
				
			}
			
			rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			if(conn != null)
			{
				try
				{
					conn.close();
					return weatherList;
					
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					return null;
					
				}
			}
		}
		
		return null;
		
    }
}
