package ics.eao;

import java.util.List;

import ics.ejb.RefereeLicense;

public interface RefereeLicenseEAOLocal {
	
	public RefereeLicense findByRefereeLicenseId(String refereeLicenseId);
	
	public RefereeLicense createRefereeLicense(RefereeLicense rl);
	
	public RefereeLicense updateRefereeLicense(RefereeLicense rl);
	
	public void deleteRefereeLicense(String refereeLicenseId);
	
	public List<RefereeLicense> findAll();
	
	public List<RefereeLicense> findByRefereeId(String refereeId);
	

}
