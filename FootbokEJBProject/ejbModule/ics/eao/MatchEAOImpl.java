package ics.eao;

import java.util.List;

import ics.ejb.Match;
import ics.ejb.Pitch;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * Session Bean implementation class Match
 */
@Stateless
public class MatchEAOImpl implements MatchEAOLocal {
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;
	
    public MatchEAOImpl() {
        // TODO Auto-generated constructor stub
    }
    
    public Match findByMatchId(long id) {
		return em.find(Match.class, id);
	}

	public Match createMatch(Match m) {
		em.persist(m);
		return m;
	}

	public Match updateMatch(Match m) {
		em.merge(m);
		return m;
	}

	public void deleteMatch(long id) {
		Match m = this.findByMatchId(id);
		if (m != null) {
			em.remove(m);
		}
	}

	public List<Match> findAll() {
		TypedQuery<Match> query = em.createNamedQuery("Match.findAll", Match.class);
		List<Match> results = query.getResultList();
		return results;
	}

	public List<Match> findByPitch(Pitch pitch) {
		TypedQuery<Match> query = em.createNamedQuery("Match.findByPitchId", Match.class);
		query.setParameter("pitchId", pitch.getPitchId());
		List<Match> results = query.getResultList();
		return results;
	}
}
