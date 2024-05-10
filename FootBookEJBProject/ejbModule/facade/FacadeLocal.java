package facade;

import java.util.List;
import java.util.Set;

import ics.ejb.Match;
import ics.ejb.Pitch;
import ics.ejb.Referee;
import ics.ejb.RefereeLicense;
import ics.ejb.User;
import ics.ejb.Pitch;
//import ics.ejb.Player;
//import ics.ejb.Referee;
//import ics.ejb.Team;
import jakarta.ejb.Local;

@Local
public interface FacadeLocal {

	// RefereeLicense Methods

	public RefereeLicense findRefereeLicense(String string);

	public List<RefereeLicense> getAllRefereeLicenses();
	

	// Referee Methods

	public List<String> findAllRefereeIds();

	public Set<Referee> getAllReferees();

	public Referee findRefereeById(String refereeId);
	
	public void createReferee(Referee referee);
	

	// Match Methods

	public Match findMatch(String string);

	public Match createMatch(Match match);

	public Match updateMatch(Match match);

	public List<Match> findAllMatches();

	public void deleteMatch(String id);

	public long getMatchCount();

	List<String> findAllMatchIds();
	

	// User Methods

	public int getUserCount();

	public int getUsersOnMatchesCount();

	public void createUser(User user);

	public Set<User> getAllUsers();
  
  public void deleteUser(String userId);
  
  
	//Pitch Methods
  
	public Pitch findPitch(String pitchId);

	public Set<Pitch> getAllPitches();

	public Set<Match> getMatchesOnPitch(String pitchId);

	public Set<User> getUsersOnMatch(String matchId);

	public List<String> findAllPitchIds();

}
