package ics.eao;

import java.util.List;

import ics.ejb.Pitch;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * Session Bean implementation class PitchEAOImpl
 */
@Stateless
public class PitchEAOImpl implements PitchEAOImplLocal {
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;

	public PitchEAOImpl() {
		
	}
	
	public Pitch findByPitchId(String pitchId) {
		return em.find(Pitch.class, pitchId);
    }
	
	public Pitch createPitch(Pitch p) {
		em.persist(p);
        return p;
    }
	
	public Pitch updatePitch(Pitch p) {
		em.merge(p);
		return p;
	}
	
	public void deletePitch(String pitchId) {
		Pitch p = this.findByPitchId(pitchId);
		if (p != null) {
			em.remove(p);
		}
	}
	
	public List<Pitch> findAll() {
		TypedQuery<Pitch> query = em.createNamedQuery("Pitch.findAll", Pitch.class);
		List<Pitch> results = query.getResultList();
		return results;
	}
}
