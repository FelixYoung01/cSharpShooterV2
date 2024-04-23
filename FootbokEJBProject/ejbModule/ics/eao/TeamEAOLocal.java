package ics.eao;

import java.util.List;

import ics.ejb.Team;
import jakarta.ejb.Local;

@Local
public interface TeamEAOLocal {
	public Team findByTeamId(String teamId);

	public Team createTeam(Team t);

	public Team updateTeam(Team t);

	public void deleteTeam(String teamId);

	public List<Team> findAll();
}
