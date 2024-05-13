package ics.eao;

import java.util.HashSet;
import java.util.Set;

import ics.ejb.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class UserEAOImpl implements UserEAOLocal{
	@PersistenceContext(unitName="FootBookEJBSql")
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
		return user;
	}
	
	public void deleteUser(String userId) {
		User user = this.findUserById(userId);
		if (user != null) {
			em.remove(user);
		}
	}
	
	public int getUserCount() {
		return em.createNamedQuery("User.countAll", Integer.class).getSingleResult();
	}
	
	public int getUsersOnMatchesCount() {
		return em.createNamedQuery("User.countRegisteredOnMatches", Integer.class).getSingleResult();
	}
	
	public Set<User> getAllUsers() {
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		return new HashSet<User>(query.getResultList());
	}
	
}
