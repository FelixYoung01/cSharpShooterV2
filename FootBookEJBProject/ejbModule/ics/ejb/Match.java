package ics.ejb;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
	private int date;
	private int time;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pitchId")
	private Pitch pitch;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "refereeId")
	private Referee referee;
	
	@ManyToMany(mappedBy = "matches", fetch = FetchType.LAZY)
	private Set<User> users;
	
	@Id
	@Column(name="matchId")
	public String getMatchId() {
		return matchId;
	}
	
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	
	@Column(name="date")
	public int getDate() {
		return date;
	}
	
	public void setDate(int date) {
		this.date = date;
	}
	
	@Column(name="time")
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	
}