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
	
	public void updateMatch(Match match) {
		em.merge(match);
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
		
		Match match = em.find(Match.class, matchId);
	    if (match == null) {
	        System.err.println("Match not found for ID: " + matchId);
	    } else {
	        System.out.println("Match found: " + match.getMatchId());
	    }
	    return match;
	}
	
	public List<Match> findAllMatches() {
		TypedQuery<Match> query = em.createNamedQuery("Match.findAll", Match.class);
		List<Match> results = query.getResultList();
		return results;
	}
	

	
	

	

}


	