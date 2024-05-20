package facade;

import java.util.List;
import java.util.Set;

import ics.ejb.Match;
import ics.ejb.Pitch;
import ics.ejb.Referee;
import ics.ejb.RefereeLicense;
import ics.ejb.User;
import ics.ejb.UserMessage;
import ics.exceptions.FootBookException;
import ics.ejb.Pitch;
//import ics.ejb.Player;
//import ics.ejb.Referee;
//import ics.ejb.Team;
import jakarta.ejb.Local;

@Local
public interface FacadeLocal {
	// RefereeLicense Methods

	public RefereeLicense findRefereeLicense(String string) throws FootBookException;

	public List<RefereeLicense> getAllRefereeLicenses() throws FootBookException;

	// Referee Methods

	public List<String> findAllRefereeIds() throws FootBookException;

	public Set<Referee> getAllReferees() throws FootBookException;

	public Referee findRefereeById(String refereeId) throws FootBookException;


	
	//public boolean isRefereeBooked(String refereeId, LocalDate date, LocalTime time);

	public void createReferee(Referee referee) throws FootBookException;

	public void deleteReferee(String refereeId) throws FootBookException;

	public Referee updateReferee(Referee refereeToUpdate) throws FootBookException;

	// Match Methods

	public Match findMatch(String string) throws FootBookException;

	public Match createMatch(Match match) throws FootBookException;

	public Match updateMatch(Match match) throws FootBookException;

	public Set<Match> findAllMatches() throws FootBookException;

	public void deleteMatch(String id) throws FootBookException;

	public long getMatchCount() throws FootBookException;

	public List<String> findAllMatchIds() throws FootBookException;

	// User Methods

	public long getUserCount() throws FootBookException;

	public long getUsersOnMatchesCount() throws FootBookException;

	public void createUser(User user) throws FootBookException;

	public Set<User> getAllUsers() throws FootBookException;

	public User findUserById(String userId) throws FootBookException;

	public User updateUser(User userToUpdate) throws FootBookException;

	public void deleteUser(String userId) throws FootBookException;

	public Set<User> getAvailableUsers() throws FootBookException;

	public User findUser(String userId) throws FootBookException;
  
  public User findUserWithMatch(String userId) throws FootBookException;

	// Pitch Methods

	public Pitch findPitch(String pitchId) throws FootBookException;

	public Set<Pitch> getAllPitches() throws FootBookException;

	public Set<Match> getMatchesOnPitch(String pitchId) throws FootBookException;

	public Set<User> getUsersOnMatch(String matchId) throws FootBookException;

	public List<String> findAllPitchIds() throws FootBookException;

	public Set<User> getUsersInMatches() throws FootBookException;

	public void addUserMessage(UserMessage userMessage) throws FootBookException;
}