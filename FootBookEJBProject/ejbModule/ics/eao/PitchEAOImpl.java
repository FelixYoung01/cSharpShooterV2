package ics.eao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ics.ejb.Pitch;
import ics.exceptions.FootBookException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;


@Stateless
public class PitchEAOImpl implements PitchEAOLocal {
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;

	public PitchEAOImpl() {
		
	}
	
	public Pitch findPitchById(String pitchId) throws FootBookException {
		if (pitchId == null) {
			throw new FootBookException("Pitch ID is null");
		}
		try {
	        Pitch pitch = em.find(Pitch.class, pitchId);
	        if (pitch == null) {
	            throw new FootBookException("Pitch not found with ID: " + pitchId);
	        }
	        return pitch;
		} catch (PersistenceException e) {
			throw new FootBookException("Error finding pitch by ID", e);
		}
	}

	public void createPitch(Pitch pitch) throws FootBookException {
		if (pitch == null) {
			throw new FootBookException("Pitch is null");
		}
		try {
			em.persist(pitch);
		} catch (PersistenceException e) {
			throw new FootBookException("Error creating pitch", e);
		}
	}

	public void updatePitch(Pitch pitch) throws FootBookException {
		if (pitch == null) {
			throw new FootBookException("Pitch is null");
		}
		try {
			em.merge(pitch);
		} catch (PersistenceException e) {
			throw new FootBookException("Error updating pitch", e);
		}
	}

	public void deletePitch(String pitchId) throws FootBookException {
		if (pitchId == null) {
			throw new FootBookException("Pitch ID is null");
		}
		try {
			Pitch pitch = em.find(Pitch.class, pitchId);
			if (pitch != null) {
				em.remove(pitch);
			}
		} catch (PersistenceException e) {
			throw new FootBookException("Error deleting pitch", e);
		}
	}

	public Set<Pitch> getAllPitches() throws FootBookException {
		try {
			TypedQuery<Pitch> query = em.createNamedQuery("Pitch.findAll", Pitch.class);
			return new HashSet<Pitch>(query.getResultList());
		} catch (PersistenceException e) {
			throw new FootBookException("Error getting all pitches", e);
		}
	}
	
	public List<String> findAllPitchIds() throws FootBookException {
		try {
	    	TypedQuery<String> query = em.createQuery("SELECT p.pitchId FROM Pitch p", String.class);
	    	return query.getResultList();
		} catch (PersistenceException e) {
			throw new FootBookException("Error getting all pitch IDs", e);
		}
	}

}
