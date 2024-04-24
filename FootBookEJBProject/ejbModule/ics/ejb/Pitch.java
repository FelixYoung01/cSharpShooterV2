package ics.ejb;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@NamedQueries({ @NamedQuery(name = "Pitch.findAll", query = "SELECT p FROM Pitch P") })
@Table(name = "Pitch")
public class Pitch implements Serializable {
	private static final long serialVersionUID = 1L;
	private String pitchId;
	private String name;

	@OneToMany(mappedBy = "pitch", fetch = FetchType.LAZY)
	private Set<Match> getMatches;

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

}