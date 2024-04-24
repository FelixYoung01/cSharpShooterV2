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
	
	/*@OneToMany(mappedBy="referee", fetch=FetchType.EAGER)
	private Set<Match> match;
	
	
	@ManyToOne
	@JoinColumn(name="RefereeLicense")
	public Set<Match> getMatch() {
        return match;
    }*/
	
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
	
	
	
	
}

