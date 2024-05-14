package ics.eao;

import java.util.List;

//import ics.ejb.Match;
//import ics.ejb.Pitch;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import ics.ejb.Match;

@Stateless

public class MatchEAOImpl implements MatchEAOLocal {
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;

	public void addMatch(Match match) {
		em.persist(match);
		
	}
	
	public Match updateMatch(Match match) {
		em.merge(match);
		return match;
	}
	
	public void deleteMatch(String matchId) {
		Match match = em.find(Match.class, matchId);
		if (match != null) {
			em.remove(match);
		}
	}
	public Match findMatchById(String matchId) {
		//Match match = em.find(Match.class, matchId);
		//return match;
		
		try {
            Match match = em.find(Match.class, matchId);
            if (match == null) {
                return null;
            }
            return match;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    
	}
	
	public List<Match> findAllMatches() {
		TypedQuery<Match> query = em.createNamedQuery("Match.findAll", Match.class);
		List<Match> results = query.getResultList();
		return results;
	}
	

	public long getMatchCount() {
		return em.createNamedQuery("Match.countAllMatches", Long.class).getSingleResult();
	}
	
	// Inside your data access class or EAO
	public List<String> findAllMatchIds() {
	    // JPA TypedQuery for retrieving only match IDs
	    TypedQuery<String> query = em.createQuery("SELECT m.matchId FROM Match m", String.class);
	    return query.getResultList();
	}



	

}


	