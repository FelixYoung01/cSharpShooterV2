package ics.ejb;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Referee.findAll", query= "SELECT r FROM Referee r")})

@Table(name="Referee")
public class Referee extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String refereeId;
	private String name;
	
	@OneToMany(mappedBy = "referee", fetch = FetchType.LAZY)
	private Set<Match> matchesRefereeing;

	@Id
	@Column(name = "refereeId")
	public String getRefereeId() {
		return refereeId;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setRefereeId(String refereeId) {
		this.refereeId = refereeId;
	}
	
	public Set<Match> getMatchesRefereeing() {
		return matchesRefereeing;
	}
	
	public void AddMatchRefereeing(Match match) {
		matchesRefereeing.add(match);
	}
	
	public void RemoveMatchRefereeing(Match match) {
		matchesRefereeing.remove(match);
	}
}