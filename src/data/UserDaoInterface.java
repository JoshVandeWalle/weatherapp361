package data;

import java.util.List;

import models.Credentials;
import models.User;

public interface UserDaoInterface extends DaoInterface<User> {
	public List<User> findByCredentials(Credentials credentials);
	public List<User> findByEmail(User record);
}
