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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


	

@Entity
@NamedQueries({ @NamedQuery(name = "Pitch.findAll", query = "SELECT p FROM Pitch p") })
@Table(name = "Pitch")

public class Pitch implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String pitchId;
	private String name;
	private Set<Match> matches; // One pitch can host many matches
  
	public Pitch(String pitchId, String name) {
		this.pitchId = pitchId;
		this.name = name;
	}
	
	public Pitch() {
	}

	@Id
	@Column(name = "pitchId")
	public String getPitchId() {
		return pitchId;
	}
	
	public void setPitchId(String pitchId) {
		this.pitchId = pitchId;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy = "pitch", fetch = FetchType.EAGER)
	public Set<Match> getMatches() {
		return matches;
	}
	
	public void setMatches(Set<Match> matches) {
		this.matches = matches;
	}	
}
