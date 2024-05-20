package ics.eao;

import ics.ejb.UserMessage;
import ics.exceptions.FootBookException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;

/**
 * Session Bean implementation class UserMessageEAOImpl
 */
@Stateless
public class UserMessageEAOImpl implements UserMessageEAOLocal {
	
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;
	
    public UserMessageEAOImpl() {
    }

    public void addUserMessage(UserMessage userMessage) throws FootBookException {
		if (userMessage == null) {
			throw new FootBookException("User message is null");
		}
        try {
            em.persist(userMessage);
        } catch (PersistenceException e) {
            throw new FootBookException("Error adding user message", e);
        }
    }
}
