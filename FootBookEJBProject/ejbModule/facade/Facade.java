package facade;

import java.util.List;

import ics.eao.MatchEAOLocal;
import ics.eao.RefereeLicenseEAOLocal;
import ics.ejb.Match;
import ics.ejb.RefereeLicense;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class Facade implements FacadeLocal{
	
	@EJB
	private RefereeLicenseEAOLocal refereeLicenseEAO;
	
	@EJB 
	private MatchEAOLocal matchEAO;
	
	public RefereeLicense findRefereeLicense(String string) {
		return refereeLicenseEAO.findRefereeLicenseById(string);
	};
	
	public Match findMatch(String string) {
		return matchEAO.findMatchById(string);
	};
	
}