package facade;

import java.util.List;
import java.util.Set;

import ics.ejb.Match;
import ics.ejb.Pitch;
import ics.ejb.RefereeLicense;
import ics.ejb.Pitch;
//import ics.ejb.Player;
//import ics.ejb.Referee;
//import ics.ejb.Team;
import jakarta.ejb.Local;

@Local
public interface FacadeLocal {

	public RefereeLicense findRefereeLicense(String string);
	
	public Match findMatch(String string);
	
	public Set<Pitch> getAllPitches();

	public List<Match> findAllMatches();

	public void deleteMatch(String id);
	
	public int getUserCount();
	
	public int getUsersOnMatchesCount();
	
	public long getMatchCount();
	
	
	public Pitch findPitch(String pitchId);

	
	public Set<Match> getMatchesOnPitch(String pitchId);

}
