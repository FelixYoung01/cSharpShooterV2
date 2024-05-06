package ics.ejb;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Referee.findAll", query = "SELECT r FROM Referee r") })
@Table(name = "Referee")

public class Referee implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	private String refereeId;
	private String refereeName;
	private Set<Match> matches;
	private RefereeLicense refereeLicense; // Each referee has exactly one referee license
	
	public Referee(String refereeId, String refereeName) {
		this.refereeId = refereeId;
		this.refereeName = refereeName;
	}
	
	public Referee() {
	}
	
	@Id
	@Column(name = "RefereeId")
	public String getRefereeId() {
		return refereeId;
	}
	
	public void setRefereeId(String refereeId) {
		this.refereeId = refereeId;
	}
	
	@Column(name = "RefereeName")
	public String getRefereeName() {
		return refereeName;
	}
	
	public void setRefereeName(String refereeName) {
		this.refereeName = refereeName;
	}

	@OneToMany(mappedBy = "referee", fetch = FetchType.LAZY)
	public Set<Match> getMatches() {
        return matches;
    }
	
	public void setMatches(Set<Match> matches) {
		this.matches = matches;
	}

	@ManyToOne(fetch = FetchType.LAZY) // Many referees can have one referee license
	@JoinColumn(name = "LicenseId") // Foreign key column
	public RefereeLicense getRefereeLicense() {
		return refereeLicense;
	}
	
	public void setRefereeLicense(RefereeLicense refereeLicense) {
		this.refereeLicense = refereeLicense;
	}
	
	
	
	
	
}

