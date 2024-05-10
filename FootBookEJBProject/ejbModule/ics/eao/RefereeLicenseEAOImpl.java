
package ics.eao;

import java.util.List;
import java.util.Set;

import ics.ejb.RefereeLicense;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

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
	
	public List<RefereeLicense> getAllRefereeLicenses() {
		TypedQuery<RefereeLicense> query = em.createNamedQuery("RefereeLicense.findAll", RefereeLicense.class);
		List<RefereeLicense> results = query.getResultList();
		return results;
	}
}