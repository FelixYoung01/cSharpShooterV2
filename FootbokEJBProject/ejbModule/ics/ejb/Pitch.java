package ics.ejb;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Pitch")
public class Pitch implements Serializable {
	private static final long serialVersionUID = 1L;
	private String pitchId;
	private String pitchName;
	private String pitchLocation;
	private String pitchType;
	
	@Id
	@Column(name="pitchId")
	public String getPitchId() {
		return pitchId;
	}
	
	public void setPitchId(String pitchId) {
		this.pitchId = pitchId;
	}
	
	@Column(name="pitchName")
	public String getPitchName() {
		return pitchName;
	}
	
	public void setPitchName(String pitchName) {
		this.pitchName = pitchName;
	}
	
	@Column(name="pitchLocation")
	public String getPitchLocation() {
		return pitchLocation;
	}
	
	public void setPitchLocation(String pitchLocation) {
		this.pitchLocation = pitchLocation;
	}
	
	@Column(name="pitchType")
	public String getPitchType() {
		return pitchType;
	}
	public void setPitchType(String pitchType) {
		this.pitchType = pitchType;
	}
}