package facade;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.migrationsupport.EnableJUnit4MigrationSupport;

import ics.eao.MatchEAOLocal;
import ics.eao.PitchEAOLocal;
import ics.eao.RefereeLicenseEAOLocal;
import ics.eao.UserEAOLocal;
import ics.ejb.Match;
import ics.ejb.Pitch;
import ics.ejb.RefereeLicense;
import ics.ejb.User;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class Facade implements FacadeLocal {
	
	@EJB
	private RefereeLicenseEAOLocal refereeLicenseEAO;
	
	@EJB 
	private MatchEAOLocal matchEAO;

	@EJB
	private PitchEAOLocal pitchEAO;
	
	@EJB
	private UserEAOLocal userEAO;
	
	
	public RefereeLicense findRefereeLicense(String string) {
		return refereeLicenseEAO.findRefereeLicenseById(string);
	};
	
	public Set<Pitch> getAllPitches() {
		return pitchEAO.getAllPitches();
	}

	
	//MATCH METHODS
	public Match findMatch(String string) {
		return matchEAO.findMatchById(string);
	};

	public List<Match> findAllMatches() {
		return matchEAO.findAllMatches();
	};
	

	public void deleteMatch(String id) {
		matchEAO.deleteMatch(id);
	}
	
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

}