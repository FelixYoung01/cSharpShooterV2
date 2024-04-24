package ics.ejb;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p")
})
@Table(name = "Player")
public class Player extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String playerId;
	private PlayerPosition playerPosition;
	private String playerTeam;
	
	@OneToOne(mappedBy = "player", fetch = FetchType.LAZY)
	private Set<Team> team;
	
	
	
	@Id
	@Column(name = "playerId")
	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	
	@Column(name = "playerPosition")
	public String getPlayerPosition() {
		return playerPosition.name();
	}

	public void setPlayerPosition(PlayerPosition playerPosition) {
		this.playerPosition = playerPosition;
	}
	
	@Column(name = "playerTeam")
	public String getPlayerTeam() {
		return playerTeam;
	}

	public void setPlayerTeam(String playerTeam) {
		this.playerTeam = playerTeam;
	}
}
