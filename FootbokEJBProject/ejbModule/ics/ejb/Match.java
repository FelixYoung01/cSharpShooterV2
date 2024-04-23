package ics.ejb;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Match")
public class Match implements Serializable {
	private static final long serialVersionUID = 1L;
	private String matchId;
	
	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
}
