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
import jakarta.persistence.JoinColumn;

@NamedQueries({ @NamedQuery(name = "RefereeLicense.findAll", query = "SELECT rl FROM RefereeLicense rl") })
@Table(name = "RefereeLicense")

@Entity

public class RefereeLicense implements Serializable {

	private static final long serialVersionUID = 1L;
	private String licenseId;
	
	@Id
	@Column(name = "LicenseId")
	public String getLicenseId() {
        return licenseId;
    }
	
	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
    }
	
	@OneToMany(mappedBy="refereeLicense", fetch=FetchType.LAZY)
	private Set<Referee> referee; // One referee license can be held by many referees
	
	public Set<Referee> getReferee() {
		return referee;
	}
	
	public void setReferee(Set<Referee> referee) {
		this.referee = referee;
	}
	
	
	
	
	}
