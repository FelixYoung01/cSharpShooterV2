package facade;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.migrationsupport.EnableJUnit4MigrationSupport;

import ics.eao.MatchEAOLocal;
import ics.eao.PitchEAOLocal;
import ics.eao.RefereeEAOLocal;
import ics.eao.RefereeLicenseEAOLocal;
import ics.eao.UserEAOLocal;
import ics.ejb.Match;
import ics.ejb.Pitch;
import ics.ejb.Referee;
import ics.ejb.RefereeLicense;
import ics.ejb.User;
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
	
	//REFEREE LICENSE METHODS
	public RefereeLicense findRefereeLicense(String string) {
		return refereeLicenseEAO.findRefereeLicenseById(string);
	};
	
	//REFEREE METHODS
	
	public List<String> findAllRefereeIds() {
		return refereeEAO.findAllRefereeIds();
	}
	
	public Referee findRefereeById(String refereeId) {
		return refereeEAO.findRefereeById(refereeId);
	}

	//PITCH METHODS
	public Set<Pitch> getAllPitches() {
		return pitchEAO.getAllPitches();
	}
	
	public List<String> findAllPitchIds() {
		return pitchEAO.findAllPitchIds();
	}

	
	//MATCH METHODS
	public Match findMatch(String string) {
		return matchEAO.findMatchById(string);
	};

	public List<Match> findAllMatches() {
		return matchEAO.findAllMatches();
	};
	
	public Match createMatch(Match match) {
		matchEAO.addMatch(match);
		return match;
	}

	public void deleteMatch(String id) {
		matchEAO.deleteMatch(id);
	}
	
	public Match updateMatch(Match match) {
		return matchEAO.updateMatch(match);
	}
	
	public List<String> findAllMatchIds() {
		return matchEAO.findAllMatchIds();
	}
	
	
	//USER METHODS
	public int getUserCount() {
		return userEAO.getUserCount();
	}
	
	public int getUsersOnMatchesCount() {
		return userEAO.getUsersOnMatchesCount();
	}
	
	public long getMatchCount() {
		return matchEAO.getMatchCount();
	}
	
	public Pitch findPitch(String pitchId) {
        return pitchEAO.findPitchById(pitchId);
	}

	
	public Set<Match> getMatchesOnPitch(String pitchId) {
		return pitchEAO.findPitchById(pitchId).getMatches();
	}
	
	public Set<User> getUsersOnMatch(String matchId) {
		return matchEAO.findMatchById(matchId).getUsers();
	}
	
	public Set<User> getAllUsers() {
		return userEAO.getAllUsers();
	}
	
	public Set<Referee> getAllReferees() {
		return refereeEAO.getAllReferees();
	}

	
	public void createUser(User user) {
		userEAO.createUser(user);
	}
	
	public void deleteUser(String userId) {
		userEAO.deleteUser(userId);
	}


}
