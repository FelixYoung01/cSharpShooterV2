package facade;

import java.util.List;
import java.util.Set;

import ics.eao.MatchEAOLocal;
import ics.eao.PitchEAOLocal;
import ics.eao.RefereeEAOLocal;
import ics.eao.RefereeLicenseEAOLocal;
import ics.eao.UserEAOLocal;
import ics.eao.UserMessageEAOLocal;
import ics.ejb.Match;
import ics.ejb.Pitch;
import ics.ejb.Referee;
import ics.ejb.RefereeLicense;
import ics.ejb.User;
import ics.ejb.UserMessage;
import ics.exceptions.FootBookException;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class Facade implements FacadeLocal {

	@EJB
	private RefereeEAOLocal refereeEAO;

	@EJB
	private RefereeLicenseEAOLocal refereeLicenseEAO;

	@EJB
	private MatchEAOLocal matchEAO;

	@EJB
	private PitchEAOLocal pitchEAO;

	@EJB
	private UserEAOLocal userEAO;
	
	@EJB
	private UserMessageEAOLocal userMessageEAO;

	// REFEREE LICENSE METHODS
	
	public RefereeLicense findRefereeLicense(String string) throws FootBookException {
		return refereeLicenseEAO.findRefereeLicenseById(string);
	}

	public List<RefereeLicense> getAllRefereeLicenses() throws FootBookException {
		return refereeLicenseEAO.getAllRefereeLicenses();
	}

	public List<String> findAllRefereeIds() throws FootBookException {
		return refereeEAO.findAllRefereeIds();
	}

	public Referee findRefereeById(String refereeId) throws FootBookException {
		return refereeEAO.findRefereeById(refereeId);
	}

	public void createReferee(Referee referee) throws FootBookException {
		refereeEAO.addReferee(referee);
	}

	public Set<Referee> getAllReferees() throws FootBookException {
		return refereeEAO.getAllReferees();
	}

	public void deleteReferee(String refereeId) throws FootBookException {
		refereeEAO.deleteReferee(refereeId);
	}

	public Referee updateReferee(Referee refereeToUpdate) throws FootBookException {
		return refereeEAO.updateReferee(refereeToUpdate);
	}

	public Set<Pitch> getAllPitches() throws FootBookException {
		return pitchEAO.getAllPitches();
	}

	public List<String> findAllPitchIds() throws FootBookException {
		return pitchEAO.findAllPitchIds();
	}

	public Pitch findPitch(String pitchId) throws FootBookException {
		return pitchEAO.findPitchById(pitchId);
	}

	public Set<Match> getMatchesOnPitch(String pitchId) throws FootBookException {
		return pitchEAO.findPitchById(pitchId).getMatches();
	}

	public Match findMatch(String string) throws FootBookException {
		return matchEAO.findMatchById(string);
	}

	public List<Match> findAllMatches() throws FootBookException {
		return matchEAO.findAllMatches();
	}

	public Match createMatch(Match match) throws FootBookException {
		matchEAO.addMatch(match);
		return match;
	}

	public void deleteMatch(String id) throws FootBookException {
		matchEAO.deleteMatch(id);
	}

	public Match updateMatch(Match match) throws FootBookException {
		return matchEAO.updateMatch(match);
	}

	public List<String> findAllMatchIds() throws FootBookException {
		return matchEAO.findAllMatchIds();
	}

	public Set<User> getUsersOnMatch(String matchId) throws FootBookException {
		return matchEAO.findMatchById(matchId).getUsers();
	}

	public long getMatchCount() throws FootBookException {
		return matchEAO.getMatchCount();
	}

	public User findUser(String userId) throws FootBookException {
		return userEAO.findUserById(userId);
	}

	public long getUserCount() throws FootBookException {
		return userEAO.getUserCount();
	}

	public long getUsersOnMatchesCount() throws FootBookException {
		return userEAO.getUsersOnMatchesCount();
	}

	public Set<User> getAvailableUsers() throws FootBookException {
		return userEAO.getAvailableUsers();
	}

	public User updateUser(User userToUpdate) throws FootBookException {
		return userEAO.updateUser(userToUpdate);
	}

	public User findUserById(String userId) throws FootBookException {
		return userEAO.findUserById(userId);
	}

	public Set<User> getAllUsers() throws FootBookException {
		return userEAO.getAllUsers();
	}

	public void createUser(User user) throws FootBookException {
		userEAO.createUser(user);
	}

	public void deleteUser(String userId) throws FootBookException {
		userEAO.deleteUser(userId);
	}

	public Set<User> getUsersInMatches() throws FootBookException {
		return userEAO.getUsersInMatches();
  }
  
	public User findUserWithMatch(String userId) throws FootBookException {
		return userEAO.findUserWithMatch(userId);
	}

	public void addUserMessage(UserMessage userMessage) throws FootBookException {
		userMessageEAO.addUserMessage(userMessage);
	}
}