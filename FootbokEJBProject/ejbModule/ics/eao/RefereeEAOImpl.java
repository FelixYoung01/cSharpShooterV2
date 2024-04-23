package ics.eao;

import java.util.List;

import ics.ejb.Referee;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 * Session Bean implementation class RefereeEAOImpl
 */
@Stateless
public class RefereeEAOImpl implements RefereeEAOLocal {
	private EntityManager em;
    public RefereeEAOImpl() {
        // TODO Auto-generated constructor stub
    }
    
    public Referee findByRefereeId(String refereeId) {
    	return em.find(Referee.class, refereeId);
    }
    
	public Referee createReferee(Referee r) {
		em.persist(r);
		return r;
	}
	
	public Referee updateReferee(Referee r) {
		em.merge(r);
		return r;
	}
	
	public void deleteReferee(String refereeId) {
		Referee r = this.findByRefereeId(refereeId);
		if (r != null) {
			em.remove(r);
		}
	}
	
	public List<Referee> findAll() {
		TypedQuery<Referee> query = em.createNamedQuery("Referee.findAll", Referee.class);
		List<Referee> results = query.getResultList();
		return results;
	}
}
