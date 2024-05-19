
package ics.eao;

import java.util.List;

import ics.ejb.RefereeLicense;
import ics.exceptions.FootBookException;
import jakarta.ejb.Local;

@Local

public interface RefereeLicenseEAOLocal {


	public void addRefereeLicense(RefereeLicense refereeLicense) throws FootBookException;

	public void updateRefereeLicense(RefereeLicense refereeLicense) throws FootBookException;

	public void deleteRefereeLicense(String refereeLicenseId) throws FootBookException;

	public RefereeLicense findRefereeLicenseById(String refereeLicenseId) throws FootBookException;
	
	public List<RefereeLicense> getAllRefereeLicenses() throws FootBookException;
}