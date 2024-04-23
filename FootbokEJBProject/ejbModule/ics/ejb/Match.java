package ics.ejb;

import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class Match
 */
@Stateless
public class Match implements MatchLocal {

	private String matchId;
	
    public Match() {
        // TODO Auto-generated constructor stub
    }

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

}
