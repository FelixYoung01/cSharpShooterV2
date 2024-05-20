package ics.eao;

import ics.ejb.UserMessage;
import ics.exceptions.FootBookException;
import jakarta.ejb.Local;

@Local
public interface UserMessageEAOLocal {
	public void addUserMessage(UserMessage userMessage) throws FootBookException;
}
