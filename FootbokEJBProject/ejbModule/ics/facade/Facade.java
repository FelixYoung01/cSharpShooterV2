package ics.facade;

import java.util.List;

import ics.eao.MatchEAOLocal;
import ics.eao.PitchEAOLocal;
import ics.eao.PlayerEAOLocal;
import ics.eao.RefereeEAOLocal;
import ics.eao.TeamEAOLocal;
import ics.ejb.Match;
import ics.ejb.Pitch;
import ics.ejb.Player;
import ics.ejb.Referee;
import ics.ejb.Team;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 * Session Bean implementation class Facade
 */
@Stateless
public class Facade implements FacadeLocal {
	
	@EJB
	MatchEAOLocal matchEAO;
	@EJB
	PitchEAOLocal pitchEAO;
	@EJB
	PlayerEAOLocal playerEAO;
	@EJB
	RefereeEAOLocal refereeEAO;
	@EJB
	TeamEAOLocal teamEAO;
	
    public Facade() {	
    }
    
    public Match findByMatchId(long id) {
		return matchEAO.findByMatchId(id);
	}

	public Match createMatch(Match m) {
		return matchEAO.createMatch(m);
	}

	public Match updateMatch(Match m) {
		return matchEAO.updateMatch(m);
	}

	public void deleteMatch(long id) {
		matchEAO.deleteMatch(id);
	}

	public List<Match> findAll() {
		return matchEAO.findAll();
	}

	public List<Match> findMatchByPitch(Pitch pitch) {
		return matchEAO.findByPitch(pitch);
	}
	
	public Pitch findByPitchId(String pitchId) {
		return pitchEAO.findByPitchId(pitchId);
	}
	
	public Pitch createPitch(Pitch p) {
		return pitchEAO.createPitch(p);
	}
	
	public Pitch updatePitch(Pitch p) {
		return pitchEAO.updatePitch(p);
	}
	
	public void deletePitch(String pitchId) {
		pitchEAO.deletePitch(pitchId);
	}
	
	public List<Pitch> findAllPitches() {
		return pitchEAO.findAll();
	}
	
	public Player findByPlayerId(String playerId) {
		return playerEAO.findByPlayerId(playerId);
	}
	
	public Player createPlayer(Player p) {
		return playerEAO.createPlayer(p);
	}
	
	public Player updatePlayer(Player p) {
		return playerEAO.updatePlayer(p);
	}
	
	public void deletePlayer(String playerId) {
		playerEAO.deletePlayer(playerId);
	}
	
	public List<Player> findAllPlayers() {
		return playerEAO.findAll();
	}
	
	public Referee findByRefereeId(String refereeId) {
		return refereeEAO.findByRefereeId(refereeId);
	}
	
	public Referee createReferee(Referee r) {
		return refereeEAO.createReferee(r);
	}
	
	public Referee updateReferee(Referee r) {
		return refereeEAO.updateReferee(r);
	}
	
	public void deleteReferee(String refereeId) {
		refereeEAO.deleteReferee(refereeId);
	}
	
	public List<Referee> findAllReferees() {
		return refereeEAO.findAll();
	}
	
	public Team findByTeamId(String teamId) {
		return teamEAO.findByTeamId(teamId);
	}
	
	public Team createTeam(Team t) {
		return teamEAO.createTeam(t);
	}
	
	public Team updateTeam(Team t) {
		return teamEAO.updateTeam(t);
	}
	
	public void deleteTeam(String teamId) {
		teamEAO.deleteTeam(teamId);
	}
	
	public List<Team> findAllTeams() {
		return teamEAO.findAll();
	}
}
