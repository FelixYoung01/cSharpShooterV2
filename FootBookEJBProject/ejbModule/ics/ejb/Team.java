package ics.ejb;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t") })
@Table(name = "Team")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;
	private String teamId;
	private String teamName;
	private Set<String> players;
	
	@ManyToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private Set<Match> matches;
	
	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private Set<Player> teamPlayers;
	

	@ManyToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private Set<Match> matches;

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private Set<Player> teamPlayers;

	@Id
	@Column(name = "teamId")
	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	@Column(name = "teamName")
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Column(name = "players")
	public Set<String> getPlayers() {
		return players;
	}

	public void setPlayers(Set<String> players) {
		this.players = players;
	}
}