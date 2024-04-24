package ics.ejb;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Match.findAll",
			query="SELECT m FROM Match m"),
	@NamedQuery(name="Match.findMatchByPitch",
            query="SELECT m FROM Match m WHERE m.pitch = :pitch")
})
@Table(name="Match")
public class Match implements Serializable {
	private static final long serialVersionUID = 1L;
	private String matchId;
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pitchId")
	private Pitch pitch;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "refereeId")
	private Referee referee;
	
	@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    private Set<Team> teams;
	
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
	
	public Referee getMatchReferee() {
		return referee;
	}
	
	public void setMatchReferee(Referee referee) {
		this.referee = referee;
	}
	
	public Set<Team> getTeams() {
		return teams;
	}
	
	public void setTeams(Set<Team> teams) {
		if (teams.size() != 2) {
			throw new IllegalArgumentException("A match must have exactly two teams!");
		}
        this.teams = teams;
    }
}