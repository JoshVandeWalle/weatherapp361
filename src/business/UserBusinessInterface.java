package business;

import models.Credentials;
import models.User;

public interface UserBusinessInterface {
	public int register(User record);
	public User authenticate(Credentials credentials);
}
