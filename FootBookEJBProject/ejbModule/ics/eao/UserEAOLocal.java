package ics.eao;

import java.util.Set;

import ics.ejb.User;
import jakarta.ejb.Local;

@Local
public interface UserEAOLocal {
	
	public void createUser(User user);
	public void updateUser(User user);
	public void deleteUser(String userId);
	public User findUserById(String userId);
	public int getUserCount(); // For statistics of how many users are registered
	public int getUsersOnMatchesCount(); //Stats on how many users are registered on matches
	public Set<User> getAllUsers();
}
