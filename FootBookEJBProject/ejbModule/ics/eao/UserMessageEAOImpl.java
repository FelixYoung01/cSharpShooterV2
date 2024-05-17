package ics.eao;

import ics.ejb.UserMessage;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Session Bean implementation class UserMessageEAOImpl
 */
@Stateless
public class UserMessageEAOImpl implements UserMessageEAOLocal {
	
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;
	
    public UserMessageEAOImpl() {
    }

    public void addUserMessage(UserMessage userMessage) {
    	em.persist(userMessage);
    }
}
