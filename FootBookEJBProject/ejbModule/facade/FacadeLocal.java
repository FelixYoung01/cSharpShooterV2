package facade;

import java.util.List;

import ics.ejb.Match;
import ics.ejb.Pitch;
import ics.ejb.Player;
import ics.ejb.Referee;
import ics.ejb.Team;
import jakarta.ejb.Local;

@Local
public interface FacadeLocal {
	public Match findByMatchId(long id);

	public Match createMatch(Match m);

	public Match updateMatch(Match m);

	public void deleteMatch(long id);

	public List<Match> findAll();

	public List<Match> findMatchByPitch(Pitch pitch);

	public Pitch findByPitchId(String pitchId);

	public Pitch createPitch(Pitch p);

	public Pitch updatePitch(Pitch p);

	public void deletePitch(String pitchId);

	public List<Pitch> findAllPitches();

	public Player findByPlayerId(String playerId);

	public Player createPlayer(Player p);

	public Player updatePlayer(Player p);

	public void deletePlayer(String playerId);

	public List<Player> findAllPlayers();

	public Referee findByRefereeId(String refereeId);

	public Referee createReferee(Referee r);

	public Referee updateReferee(Referee r);

	public void deleteReferee(String refereeId);

	public List<Referee> findAllReferees();

	public Team findByTeamId(String teamId);

	public Team createTeam(Team t);

	public Team updateTeam(Team t);

	public void deleteTeam(String teamId);

	public List<Team> findAllTeams();
}