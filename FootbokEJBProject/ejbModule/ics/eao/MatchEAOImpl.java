package ics.eao;

import ics.ejb.Match;
import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class Match
 */
@Stateless
public class MatchEAOImpl implements MatchEAOLocal {

	private Match match;
	
    public MatchEAOImpl() {
        // TODO Auto-generated constructor stub
    }

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
}
