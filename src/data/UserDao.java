package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Credentials;
import models.User;
import models.Weather;

public class UserDao implements UserDaoInterface {
	
	private String url;
	private String username;
	private String password;
	
	public UserDao(String url, String username, String password)
	{
		this.url = url;
		this.username = username;
		this.password = password;
	}

	@Override
	public int create(User record) {
		Connection conn = null;
		String sql = "INSERT INTO weatherapp.user(EMAIL, PASSWORD) VALUES('" + record.getEmail() + "','" + record.getPassword() + "')";
		try
		{
			conn = DriverManager.getConnection(this.url, this.username, this.password);
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

	@Override
	public List<User> findByCredentials(Credentials credentials) {
		String sql = "SELECT * FROM `user` WHERE EMAIL = '" + credentials.getEmail() + "' AND PASSWORD = '" + credentials.getPassword() +"'";
		Connection conn = null;
		List<User> userList = new ArrayList<User>();
		
		try
		{
			System.out.println("=====>SHOULD BE OBTAINING RESULT<=====");
			conn = DriverManager.getConnection(this.url, this.username, this.password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				//Credentials userCredentials = new Credentials(rs.getString("EMAIL"), rs.getString("PASSWORD"));
				userList.add(new User(rs.getInt("ID"), rs.getString("EMAIL"), rs.getString("PASSWORD")));
				
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
					return userList;
					
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

	@Override
	public List<User> findByEmail(User record) {
		String sql = "SELECT * FROM `user` WHERE EMAIL = '" + record.getEmail() + "'";
		Connection conn = null;
		List<User> userList = new ArrayList<User>();
		
		try
		{
			System.out.println("=====>SHOULD BE OBTAINING RESULT<=====");
			conn = DriverManager.getConnection(this.url, this.username, this.password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				//Credentials userCredentials = new Credentials(rs.getString("EMAIL"), rs.getString("PASSWORD"));
				userList.add(new User(rs.getInt("ID"), rs.getString("EMAIL"), rs.getString("PASSWORD")));
				
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
					return userList;
					
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
