package ics.eao;

import java.util.List;

import ics.ejb.RefereeLicense;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class RefereeLicenseEAOImpl implements RefereeLicenseEAOLocal {
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;
	
	public RefereeLicenseEAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public RefereeLicense findByRefereeLicenseId(String refereeLicenseId) {
		return em.find(RefereeLicense.class, refereeLicenseId);
	}
	
	public RefereeLicense createRefereeLicense(RefereeLicense rl) {
		em.persist(rl);
		return rl;
	}
	
	public RefereeLicense updateRefereeLicense(RefereeLicense rl) {
		em.merge(rl);
		return rl;
	}
	
	public void deleteRefereeLicense(String refereeLicenseId) {
		RefereeLicense rl = this.findByRefereeLicenseId(refereeLicenseId);
		if (rl != null) {
			em.remove(rl);
		}
	}
	
	public List<RefereeLicense> findAll() {
		TypedQuery <RefereeLicense> query = em.createNamedQuery("RefereeLicense.findAll", RefereeLicense.class);	
		List<RefereeLicense> results = query.getResultList();
		return results;
	}
	
	public List<RefereeLicense> findByRefereeId(String refereeId) {
		TypedQuery <RefereeLicense> query = em.createNamedQuery("RefereeLicense.findByRefereeId", RefereeLicense.class);
		query.setParameter("refreeId", refereeId);
		List<RefereeLicense> results = query.getResultList();
		return results;
	}
	
	
	

}
