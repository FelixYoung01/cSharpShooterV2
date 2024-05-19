
package ics.eao;

import java.util.List;

import ics.ejb.RefereeLicense;
import ics.exceptions.FootBookException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

@Stateless
public class RefereeLicenseEAOImpl implements RefereeLicenseEAOLocal {
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;

	public void addRefereeLicense(RefereeLicense refereeLicense) throws FootBookException {
		if (refereeLicense == null) {
			throw new FootBookException("Referee license is null");
		}
		try {
			em.persist(refereeLicense);
		} catch (PersistenceException e) {
			throw new FootBookException("Error adding referee license", e);
		}
	}
	
	public void updateRefereeLicense(RefereeLicense refereeLicense) throws FootBookException {
		if (refereeLicense == null) {
			throw new FootBookException("Referee license is null");
		}
		try {
			if (em.find(RefereeLicense.class, refereeLicense.getLicenseId()) == null) {
				throw new FootBookException("Referee license not found");
			}
			em.merge(refereeLicense);
		} catch (PersistenceException e) {
			throw new FootBookException("Error updating referee license", e);
		}
	}
	
	public void deleteRefereeLicense(String refereeLicenseId) throws FootBookException {
		if (refereeLicenseId == null) {
			throw new FootBookException("Referee license ID is null");
		}
		try {
			RefereeLicense refereeLicense = em.find(RefereeLicense.class, refereeLicenseId);
			if (refereeLicense == null) {
				throw new FootBookException("Referee license not found");
			}
			em.remove(refereeLicense);
		} catch (PersistenceException e) {
			throw new FootBookException("Error deleting referee license", e);
		}
	}
	
	public RefereeLicense findRefereeLicenseById(String refereeLicenseId) throws FootBookException {
		if (refereeLicenseId == null) {
			throw new FootBookException("Referee license ID is null");
		}
		try {
			RefereeLicense refereeLicense = em.find(RefereeLicense.class, refereeLicenseId);
			if (refereeLicense == null) {
				throw new FootBookException("Referee license not found");
			}
			return refereeLicense;
		} catch (PersistenceException e) {
			throw new FootBookException("Error finding referee license by ID", e);
		}
	}
	
	public List<RefereeLicense> getAllRefereeLicenses() throws FootBookException {
		try {
			TypedQuery<RefereeLicense> query = em.createNamedQuery("RefereeLicense.findAll", RefereeLicense.class);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new FootBookException("Error getting all referee licenses", e);
		}
	}
}