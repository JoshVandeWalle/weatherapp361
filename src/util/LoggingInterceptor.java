package util;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * This class intercepts method calls and logs them
 * @author tJosh Van de Walle
 *
 */
public class LoggingInterceptor implements Serializable {

	/**
	 * This field is needed because the class implements Serializable 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * This method prints a log message to the console that indicates what class and method are being intercepted 
	 * @param ctx the information about the call
	 * @return an object that tells the program to continue to the method call that was intercepted
	 * @throws Exception
	 */
	@AroundInvoke 
	public Object methodInterceptor(InvocationContext ctx) throws Exception 
	{ 
		// print the message
		System.out.println("******************* Intercepting call to method: " +  
		ctx.getTarget().getClass() + "." + ctx.getMethod().getName() + "()"); 
		// proceed with execution
		return ctx.proceed(); 
	}
}
