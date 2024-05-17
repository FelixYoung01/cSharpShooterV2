
package ics.eao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//import ics.ejb.Match;
//import ics.ejb.Pitch;
import jakarta.ejb.Local;

import ics.ejb.Match;

@Local
public interface MatchEAOLocal {

	public void addMatch(Match match);

	public Match updateMatch(Match match);

	public void deleteMatch(String matchId);

	public Match findMatchById(String matchId);

	public List<Match> findAllMatches();
	
	public long getMatchCount();

	public List<String> findAllMatchIds();
	
	public List<Match> findMatchesByRefereeAndDateTime(String refereeId, LocalDate date, LocalTime time);
	
	public boolean isMatchUnique(String pitchId, LocalDate date, LocalTime time);
}