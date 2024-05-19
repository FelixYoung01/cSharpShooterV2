
package ics.eao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ics.ejb.Referee;
import ics.exceptions.FootBookException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

@Stateless
public class RefereeEAOImpl implements RefereeEAOLocal {
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;

	public Set<Referee> getAllReferees() throws FootBookException {
		try {
			TypedQuery<Referee> query = em.createNamedQuery("Referee.findAll", Referee.class);
			return new HashSet<Referee>(query.getResultList());
		} catch (PersistenceException e) {
			throw new FootBookException("Error getting all referees", e);
		}
	}

	public void addReferee(Referee referee) throws FootBookException {
		if (referee == null) {
			throw new FootBookException("Referee is null");
		}
		try {
			em.persist(referee);
		} catch (PersistenceException e) {
			throw new FootBookException("Error adding referee", e);
		}
	}
	
	public Referee updateReferee(Referee referee) throws FootBookException {
		if (referee == null) {
			throw new FootBookException("Referee is null");
		}
		try {
			if (em.find(Referee.class, referee.getRefereeId()) == null) {
				throw new FootBookException("Referee not found");
			}
			em.merge(referee);
			em.flush();
			return referee;
		} catch (PersistenceException e) {
			throw new FootBookException("Error updating referee", e);
		}
	}


	public void deleteReferee(String refereeId) throws FootBookException {
		if (refereeId == null) {
			throw new FootBookException("Referee ID is null");
		}
		try {
			Referee referee = em.find(Referee.class, refereeId);
			if (referee == null) {
				throw new FootBookException("Referee not found");
			}
			em.remove(referee);
		} catch (PersistenceException e) {
			throw new FootBookException("Error deleting referee", e);
		}
	}


	public Referee findRefereeById(String refereeId) throws FootBookException {
		if (refereeId == null) {
			throw new FootBookException("Referee ID is null");
		}
		try {
			return em.find(Referee.class, refereeId);
		} catch (PersistenceException e) {
			throw new FootBookException("Error finding referee by ID", e);
		}
	}
	
	public List<String> findAllRefereeIds() throws FootBookException {
		try {
	    	TypedQuery<String> query = em.createQuery("SELECT r.refereeId FROM Referee r", String.class);
	    	return query.getResultList();
		} catch (PersistenceException e) {
			throw new FootBookException("Error getting all referee IDs", e);
		}
	}
}
