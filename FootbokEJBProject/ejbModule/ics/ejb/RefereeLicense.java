package ics.ejb;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="RefereeLicense")
public class RefereeLicense implements Serializable {
	private static final long serialVersionUID = 1L;
	private String refereeLicenseId;
	private String refereeLicenseType;

	@Id
	@Column(name = "refereeLicenseId")
	public String getRefereeLicenseId() {
		return refereeLicenseId;
	}

	public void setRefereeLicenseId(String refereeLicenseId) {
		this.refereeLicenseId = refereeLicenseId;
	}

	@Column(name = "refereeLicenseType")
	public String getRefereeLicenseType() {
		return refereeLicenseType;
	}

	public void setRefereeLicenseType(String refereeLicenseType) {
		this.refereeLicenseType = refereeLicenseType;
	}
}
