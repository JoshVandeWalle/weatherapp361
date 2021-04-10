package business;

import models.Credentials;
import models.User;

/**
 * This interface defines the business logic API for users
 * @author Josh Van de Walle
 *
 */
public interface UserBusinessInterface {
	public int register(User record);
	public User authenticate(Credentials credentials);
}
