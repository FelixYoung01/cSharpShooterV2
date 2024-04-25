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
@NamedQueries({ @NamedQuery(name = "Match.findAll", query = "SELECT m FROM Match m") })
@Table(name = "Matches")

public class Match implements Serializable {

	private static final long serialVersionUID = 1L;
	private String matchId;
	private Set<Pitch> pitch;
	private Date date;
	private int time;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="refereeId") public Set<Referee> getReferee() { return
	 * referee; }
	 */

	@Id
	@Column(name = "matchId")
	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	@ManyToOne
	@JoinColumn(name = "pitchId")
	public Set<Pitch> getPitch() {
		return pitch;
	}

	public void setPitch(Set<Pitch> pitch) {
		this.pitch = pitch;
	}

	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "time")
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	/*
	 * public void setPitch(Set<Pitch> pitch) { this.pitch = pitch; }
	 */

	/*
	 * public void setReferee(Set<Referee> referee) { this.referee = referee; }
	 */

}
