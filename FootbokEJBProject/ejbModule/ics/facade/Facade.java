package ics.facade;

import java.util.List;

import ics.eao.MatchEAOLocal;
import ics.ejb.Match;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class Facade
 */
@Stateless
public class Facade implements FacadeLocal {
	
	@EJB
	MatchEAOLocal matchEAO;
	
    public Facade() {	
    }
    
    public Match findByMatchId(long id) {
		return matchEAO.findByMatchId(id);
	}

	public Match createMatch(Match m) {
		return matchEAO.createMatch(m);
	}

	public Match updateMatch(Match m) {
		return matchEAO.updateMatch(m);
	}

	public void deleteMatch(long id) {
		matchEAO.deleteMatch(id);
	}

	public List<Match> findAll() {
		return matchEAO.findAll();
	}

	public List<Match> findByPitchId(String pitchId) {
		return matchEAO.findByPitchId(pitchId);
	}
}
