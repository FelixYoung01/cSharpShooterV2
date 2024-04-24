package ics.ejb;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@NamedQuery(name="Match.findAll",
query="SELECT m FROM Match m")
@Table(name="Match")
public class Match implements Serializable {
	private static final long serialVersionUID = 1L;
	private String matchId;
	private Date date;
	private String matchReferee;
	private String matchHomeTeam;
	private String matchAwayTeam;
	
	@Id
	@Column(name="matchId")
	public String getMatchId() {
		return matchId;
	}
	
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	
	@Column(name="date")
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name="matchReferee")
	public String getMatchReferee() {
		return matchReferee;
	}
	
	public void setMatchReferee(String matchReferee) {
		this.matchReferee = matchReferee;
	}
	
	@Column(name="matchHomeTeam")
	public String getMatchHomeTeam() {
		return matchHomeTeam;
	}
	
	public void setMatchHomeTeam(String matchHomeTeam) {
		this.matchHomeTeam = matchHomeTeam;
	}
	
	@Column(name="matchAwayTeam")
	public String getMatchAwayTeam() {
		return matchAwayTeam;
	}
	public void setMatchAwayTeam(String matchAwayTeam) {
		this.matchAwayTeam = matchAwayTeam;
	}
}
