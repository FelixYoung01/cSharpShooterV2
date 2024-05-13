package ics.eao;

import java.util.Set;

import ics.ejb.User;
import jakarta.ejb.Local;

@Local
public interface UserEAOLocal {

	public void createUser(User user);

	public User updateUser(User user);

	public void deleteUser(String userId);

	public User findUserById(String userId);

	public long getUserCount(); // For statistics of how many users are registered
	public long getUsersOnMatchesCount(); //Stats on how many users are registered on matches

	public Set<User> getAllUsers();

	public Set<User> getAvailableUsers();

}
