package ics.eao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ics.ejb.Match;
import ics.ejb.User;
import ics.exceptions.FootBookException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

@Stateless
public class UserEAOImpl implements UserEAOLocal {
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;

	public UserEAOImpl() {
		
	}

	public User findUserById(String userId) throws FootBookException {
		if (userId == null) {
			throw new FootBookException("User ID is null");
		}
		try {
			User user = em.find(User.class, userId);
			if (user == null) {
				throw new FootBookException("User not found");
			}
			return user;
		} catch (PersistenceException e) {
			throw new FootBookException("Error finding user by ID", e);
		}
	}

	public void createUser(User user) throws FootBookException {
		if (user == null) {
			throw new FootBookException("User is null");
		}
		try {
			em.persist(user);
		} catch (PersistenceException e) {
			throw new FootBookException("Error creating user", e);
		}
	}

	public User updateUser(User user) throws FootBookException {
		if (user == null) {
			throw new FootBookException("User is null");
		}
		try {
			if (em.find(User.class, user.getUserId()) == null) {
				throw new FootBookException("User not found");
			}
			em.merge(user);
			em.flush();
			return user;
		} catch (PersistenceException e) {
			throw new FootBookException("Error updating user", e);
		}
	}

	public void deleteUser(String userId) throws FootBookException {
		if (userId == null) {
			throw new FootBookException("User ID is null");
		}
		try {
			User user = em.find(User.class, userId);
			if (user == null) {
				throw new FootBookException("User not found");
			}
			em.remove(user);
		} catch (PersistenceException e) {
			throw new FootBookException("Error deleting user", e);
		}
	}

	public long getUserCount() throws FootBookException {
		try {
			return em.createNamedQuery("User.countAll", Long.class).getSingleResult();
		} catch (PersistenceException e) {
			throw new FootBookException("Error getting user count", e);
		}
	}

	public long getUsersOnMatchesCount() throws FootBookException {
		try {
			return em.createNamedQuery("User.countRegisteredOnMatches", Long.class).getSingleResult();
		} catch (PersistenceException e) {
			throw new FootBookException("Error getting user count on matches", e);
		}
	}

	public Set<User> getAllUsers() throws FootBookException {
		try {
			TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
			return new HashSet<User>(query.getResultList());
		} catch (PersistenceException e) {
			throw new FootBookException("Error getting all users", e);
		}
	}

	public Set<User> getAvailableUsers() throws FootBookException {
		try {
			TypedQuery<User> query = em.createNamedQuery("User.availableUsers", User.class);
			return new HashSet<User>(query.getResultList());
		} catch (PersistenceException e) {
			throw new FootBookException("Error getting available users", e);
		}
	}

	
	public Set<User> getUsersInMatches() throws FootBookException {
		try {
			TypedQuery<Match> query = em.createNamedQuery("Match.findAll", Match.class);
			List<Match> matches = query.getResultList();
			Set<User> usersInMatches = new HashSet<>();

			for (Match match : matches) {
				usersInMatches.addAll(match.getUsers());
			}

			// Log the retrieved users
			for (User user : usersInMatches) {
				System.out.println("User in match: " + user.getUserId() + ", " + user.getName());
			}

			return usersInMatches;
		} catch (PersistenceException e) {
			throw new FootBookException("Error getting users in matches", e);
		}
	}
}
