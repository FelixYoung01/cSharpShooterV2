package facade;

import java.util.List;

import ics.eao.RefereeLicenseEAOLocal;
import ics.ejb.RefereeLicense;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class Facade implements FacadeLocal{
	
	@EJB
	private RefereeLicenseEAOLocal refereeLicenseEAO;
	
	public RefereeLicense findRefereeLicense(String string) {
		return refereeLicenseEAO.findRefereeLicenseById(string);
	};
	
}