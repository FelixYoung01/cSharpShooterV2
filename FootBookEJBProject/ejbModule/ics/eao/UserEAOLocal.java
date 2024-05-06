package ics.eao;

import ics.ejb.User;
import jakarta.ejb.Local;

@Local
public interface UserEAOLocal {
	
	public void createUser(User user);
	public void updateUser(User user);
	public void deleteUser(String userId);
	public User findUserById(String userId);
	
	
	
}
