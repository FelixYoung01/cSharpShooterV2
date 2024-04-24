package ics.ejb;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Referee.findAll", query= "SELECT r FROM Referee r")})

@Table(name="Referee")
public class Referee implements Serializable {
	private static final long serialVersionUID = 1L;
	private String refereeId;
	private String refereeName;
	private String refereeType;

	@Id
	@Column(name = "refereeId")
	public String getRefereeId() {
		return refereeId;
	}

	public void setRefereeId(String refereeId) {
		this.refereeId = refereeId;
	}

	@Column(name = "refereeName")
	public String getRefereeName() {
		return refereeName;
	}

	public void setRefereeName(String refereeName) {
		this.refereeName = refereeName;
	}

	@Column(name = "refereeType")
	public String getRefereeType() {
		return refereeType;
	}

	public void setRefereeType(String refereeType) {
		this.refereeType = refereeType;
	}
}