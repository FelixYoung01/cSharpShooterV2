package ics.eao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ics.ejb.Pitch;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@Stateless
public class PitchEAOImpl implements PitchEAOLocal {
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;

	public PitchEAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public Pitch findPitchById(String pitchId) {
		return em.find(Pitch.class, pitchId);
	}

	public void createPitch(Pitch pitch) {
		em.persist(pitch);
	}

	public void updatePitch(Pitch pitch) {
		em.merge(pitch);
	}

	public void deletePitch(String pitchId) {
		Pitch pitch = this.findPitchById(pitchId);
		if (pitch != null) {
			em.remove(pitch);
		}
	}

	public Set<Pitch> getAllPitches() {
		TypedQuery<Pitch> query = em.createNamedQuery("Pitch.findAll", Pitch.class);
		return new HashSet<Pitch>(query.getResultList());
	}
	
	public List<String> findAllPitchIds() {
	    TypedQuery<String> query = em.createQuery("SELECT p.pitchId FROM Pitch p", String.class);
	    return query.getResultList();
	}

}
