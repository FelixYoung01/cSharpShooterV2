
package ics.eao;

import java.util.List;

import ics.ejb.RefereeLicense;

import jakarta.ejb.Local;

@Local

public interface RefereeLicenseEAOLocal {


	public void addRefereeLicense(RefereeLicense refereeLicense);

	public void updateRefereeLicense(RefereeLicense refereeLicense);

	public void deleteRefereeLicense(String refereeLicenseId);

	public RefereeLicense findRefereeLicenseById(String refereeLicenseId);
	
	public List<RefereeLicense> getAllRefereeLicenses();
}