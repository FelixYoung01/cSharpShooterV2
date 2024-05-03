
package ics.eao;

import ics.ejb.RefereeLicense;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class RefereeLicenseEAOImpl implements RefereeLicenseEAOLocal {
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;

	public void addRefereeLicense(RefereeLicense refereeLicense) {
		em.persist(refereeLicense);
	}
	
	public void updateRefereeLicense(RefereeLicense refereeLicense) {
		em.merge(refereeLicense);
	}
	
	public void deleteRefereeLicense(String refereeLicenseId) {
		RefereeLicense refereeLicense = em.find(RefereeLicense.class, refereeLicenseId);
		if (refereeLicense != null) {
			em.remove(refereeLicense);
		}
	}
	
	public RefereeLicense findRefereeLicenseById(String refereeLicenseId) {
		RefereeLicense refereeLicense = em.find(RefereeLicense.class, refereeLicenseId);
		return refereeLicense;
	}
}