package ics.eao;

import ics.ejb.UserMessage;
import jakarta.ejb.Local;

@Local
public interface UserMessageEAOLocal {
	public void addUserMessage(UserMessage userMessage);
}
