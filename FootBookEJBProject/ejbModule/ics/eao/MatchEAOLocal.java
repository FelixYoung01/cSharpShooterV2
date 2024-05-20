
package ics.eao;

import java.util.List;
import java.util.Set;

//import ics.ejb.Match;
//import ics.ejb.Pitch;
import jakarta.ejb.Local;

import ics.ejb.Match;
import ics.exceptions.FootBookException;

@Local
public interface MatchEAOLocal {

	public void addMatch(Match match) throws FootBookException;

	public Match updateMatch(Match match) throws FootBookException;

	public void deleteMatch(String matchId) throws FootBookException;

	public Match findMatchById(String matchId) throws FootBookException;

	public Set<Match> findAllMatches() throws FootBookException;
	
	public long getMatchCount() throws FootBookException;

	public List<String> findAllMatchIds() throws FootBookException;
	
}