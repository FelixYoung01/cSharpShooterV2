
package ics.eao;

import java.util.List;

//import ics.ejb.Match;
//import ics.ejb.Pitch;
import jakarta.ejb.Local;

import ics.ejb.Match;

@Local
public interface MatchEAOLocal {

	public void addMatch(Match match);

	public void updateMatch(Match match);

	public void deleteMatch(String matchId);

	public Match findMatchById(String matchId);
}