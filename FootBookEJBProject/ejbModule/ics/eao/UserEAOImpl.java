package ics.eao;

import ics.ejb.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
	
	public void updateUser(User user) {
		em.merge(user);
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

	
}
