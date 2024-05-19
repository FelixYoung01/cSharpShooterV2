package ics.eao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ics.ejb.Match;
import ics.ejb.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class UserEAOImpl implements UserEAOLocal {
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;

	public UserEAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public User findUserById(String userId) {
		return em.find(User.class, userId);
	}

	public void createUser(User user) {
		em.persist(user);
	}

	public User updateUser(User user) {
		em.merge(user);
		em.flush();
		return user;
	}

	public void deleteUser(String userId) {
		User user = this.findUserById(userId);
		if (user != null) {
			em.remove(user);
		}
	}

	public long getUserCount() {
		return em.createNamedQuery("User.countAll", Long.class).getSingleResult();
	}

	public long getUsersOnMatchesCount() {
		return em.createNamedQuery("User.countRegisteredOnMatches", Long.class).getSingleResult();
	}

	public Set<User> getAllUsers() {
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		return new HashSet<User>(query.getResultList());
	}

	public Set<User> getAvailableUsers() {
		TypedQuery<User> query = em.createNamedQuery("User.availableUsers", User.class);
		return new HashSet<User>(query.getResultList());
	}

	
	public Set<User> getUsersInMatches() {
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
	}
	
	public User findUserWithMatch(String userId) {
		TypedQuery<User> query = em.createNamedQuery("User.findUserWithMatch", User.class);
		query.setParameter("userId", userId);
		return query.getSingleResult();
	}
}
