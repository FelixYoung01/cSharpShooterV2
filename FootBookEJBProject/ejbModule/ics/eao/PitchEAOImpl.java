package ics.eao;

import java.util.List;

import ics.ejb.Pitch;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@Stateless
public class PitchEAOImpl {
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
}