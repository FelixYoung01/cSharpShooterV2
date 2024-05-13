
package ics.eao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ics.ejb.Referee;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class RefereeEAOImpl implements RefereeEAOLocal {
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;

	public Set<Referee> getAllReferees() {
		TypedQuery<Referee> query = em.createNamedQuery("Referee.findAll", Referee.class);
		return new HashSet<Referee>(query.getResultList());
	}

	public void addReferee(Referee referee) {
		em.persist(referee);
	}
	
	public Referee updateReferee(Referee referee) {
		em.merge(referee);
		em.flush();
		return referee;
	}


	public void deleteReferee(String refereeId) {
		Referee referee = em.find(Referee.class, refereeId);
		if (referee != null) {
			em.remove(referee);
		}
	}


	public Referee findRefereeById(String refereeId) {
		Referee referee = em.find(Referee.class, refereeId);
		return referee;
	}
	
	public List<String> findAllRefereeIds() {
	    TypedQuery<String> query = em.createQuery("SELECT r.refereeId FROM Referee r", String.class);
	    return query.getResultList();
	}

}
