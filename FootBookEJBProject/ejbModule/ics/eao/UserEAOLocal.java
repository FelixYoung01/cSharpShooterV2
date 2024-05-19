package ics.eao;

import java.util.Set;

import ics.ejb.User;
import ics.exceptions.FootBookException;
import jakarta.ejb.Local;

@Local
public interface UserEAOLocal {

	public void createUser(User user) throws FootBookException;

	public User updateUser(User user) throws FootBookException;

	public void deleteUser(String userId) throws FootBookException;

	public User findUserById(String userId) throws FootBookException;

	public long getUserCount() throws FootBookException; // For statistics of how many users are registered
	public long getUsersOnMatchesCount() throws FootBookException; //Stats on how many users are registered on matches

	public Set<User> getAllUsers() throws FootBookException;

	public Set<User> getAvailableUsers() throws FootBookException;
	
	public Set<User> getUsersInMatches() throws FootBookException;


}
