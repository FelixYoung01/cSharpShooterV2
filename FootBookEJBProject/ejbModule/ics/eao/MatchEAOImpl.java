package ics.eao;

import java.util.List;

//import ics.ejb.Match;
//import ics.ejb.Pitch;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

import ics.ejb.Match;
import ics.exceptions.FootBookException;

@Stateless

public class MatchEAOImpl implements MatchEAOLocal {
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;

	public void addMatch(Match match) throws FootBookException {
		if (match == null) {
			throw new FootBookException("Match is null");
		}
		try {
			em.persist(match);
		} catch (PersistenceException e) {
			throw new FootBookException("Error adding match", e);
		}
	}
	
	public Match updateMatch(Match match) throws FootBookException {
		if (match == null) {
			throw new FootBookException("Match is null");
		}
		try {
			em.merge(match);
			em.flush();
			return match;
		} catch (PersistenceException e) {
			throw new FootBookException("Error updating match", e);
		}
	}
	
	public void deleteMatch(String matchId) throws FootBookException {
		if (matchId == null) {
			throw new FootBookException("Match ID is null");
		}
		try {
			Match match = em.find(Match.class, matchId);
			if (match != null) {
				em.remove(match);
			}
		} catch (PersistenceException e) {
			throw new FootBookException("Error deleting match", e);
		}
	}
	public Match findMatchById(String matchId) throws FootBookException {
		if (matchId == null) {
			throw new FootBookException("Match ID is null");
		}
		try {
            Match match = em.find(Match.class, matchId);
			if (match == null) {
				throw new FootBookException("Match not found with ID: " + matchId);
			}
            return match;
        } catch (PersistenceException e) {
            throw new FootBookException("Error finding match by ID", e);
        }
	}
	
	public List<Match> findAllMatches() throws FootBookException {
		try {
			TypedQuery<Match> query = em.createNamedQuery("Match.findAll", Match.class);
			List<Match> results = query.getResultList();
			return results;
		} catch (PersistenceException e) {
			throw new FootBookException("Error finding all matches", e);
		}
	}
	

	public long getMatchCount() throws FootBookException {
		try {
			return em.createNamedQuery("Match.countAllMatches", Long.class).getSingleResult();
		} catch (PersistenceException e) {
			throw new FootBookException("Error counting all matches", e);
		}
	}
	
	public List<String> findAllMatchIds() throws FootBookException {
	    try {
	        TypedQuery<String> query = em.createQuery("SELECT m.matchId FROM Match m", String.class);
	        return query.getResultList();
	    } catch (PersistenceException e) {
	        throw new FootBookException("Error finding all match IDs", e);
	    }
	}
}