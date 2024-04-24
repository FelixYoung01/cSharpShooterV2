
package ics.eao;

import java.util.List;

import ics.ejb.Referee;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class RefereeEAOImpl implements RefereeEAOLocal {
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;

	@Override
	public List<Referee> getAllReferees() {
		TypedQuery<Referee> query = em.createNamedQuery("Referee.findAll", Referee.class);
		List<Referee> referees = query.getResultList();
		return referees;
	}

	public void addReferee(Referee referee) {
		em.persist(referee);
	}
	
	public void updateReferee(Referee referee) {
		em.merge(referee);
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
}
