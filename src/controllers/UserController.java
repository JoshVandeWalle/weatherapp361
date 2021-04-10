package controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.ws.rs.Path;
import business.UserBusinessService;
import models.Credentials;
import models.User;

/**
 * This class manages behavior related to users
 * @author Josh Van de Walle and Austin Harvey
 *
 */
@Named
@ViewScoped
@Path("/user")
@Interceptors(util.LoggingInterceptor.class) 
public class UserController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// business logic service for users
	UserBusinessService service;
	
	/**
	 * This method manages behavior related to registering a user
	 * @param user the user to register
	 * @return the appropriate view
	 */
	public String handleRegister(User user)
	{
		// use try/catch to handle exceptions
		try 
		{
			service = new UserBusinessService();
			// pass control to business layer for registration
			if (service.register(user) == 1)
			{
				return "Index.xhtml";
			}
			
			else 
			{
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
				return "Register.xhtml";
			}
		}
		
		// handle exceptions here
		catch (Exception e)
		{
			return "Error.xhtml";
		}
	}
	
	/**
	 * This method manages behavior related to authenticating a user
	 * @param credentials the user credentials to authenticate
	 * @return the appropriate view 
	 */
	public String handleLogin(Credentials credentials)
	{
		// use try/catch to handle exceptions
		try 
		{
			service = new UserBusinessService();
			
			// pass control to business layer for authentication
			if (service.authenticate(credentials) != null)
			{
				return "Index.xhtml";
			}
			
			else 
			{
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("credentials", credentials);
				return "Login.xhtml";
			}
		}
		
		// handle exceptions here
		catch (Exception e)
		{
			return "Error.xhtml";
		}
	}
}
