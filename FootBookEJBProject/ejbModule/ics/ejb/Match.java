package ics.ejb;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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
	private Pitch pitch;
	private LocalDate date;
	private LocalTime time;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="refereeId") public Set<Referee> getReferee() { return
	 * referee; }
	 */
	public Match() {
	}
	
	public Match(String matchId, Pitch pitch, LocalDate date2, LocalTime time2) {
		this.matchId = matchId;
		this.pitch = pitch;
		this.date = date2;
		this.time = time2;
	}

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
	public Pitch getPitch() {
		return pitch;
	}

	public void setPitch(Pitch pitch) {
		this.pitch = pitch;
	}

	@Column(name = "date")
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Column(name = "time")
	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	/*
	 * public void setPitch(Set<Pitch> pitch) { this.pitch = pitch; }
	 */

	/*
	 * public void setReferee(Set<Referee> referee) { this.referee = referee; }
	 */

}
