package business;

import java.util.List;
import data.UserDao;
import models.Credentials;
import models.User;


//@Interceptors(util.LoggingInterceptor.class) 
public class UserBusinessService implements UserBusinessInterface {

	UserDao dao;
	
	@Override
	public int register(User record) {
		dao = new UserDao("jdbc:mysql://localhost:3306/weatherapp", "root", "root");
		
		
		if (dao.findByEmail(record).size() > 0)
		{
			return -1;
		}

		return dao.create(record);
	}

	@Override
	public User authenticate(Credentials credentials) {
		dao = new UserDao("jdbc:mysql://localhost:3306/weatherapp", "root", "root");
		List<User> userList = dao.findByCredentials(credentials);
		
		if (userList.size() == 1)
		{
			return userList.get(0);
		}
		
		else 
		{
			return null;
		}
	}

}
