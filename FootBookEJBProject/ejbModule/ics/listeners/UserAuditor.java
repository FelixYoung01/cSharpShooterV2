package ics.listeners;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;
import java.util.logging.Logger;
import ics.ejb.User;

public class UserAuditor {
	private static final Logger logger = Logger.getLogger(UserAuditor.class.getName());

	// every time a user is created, the user will be logged as created with the
	// user's ID and name
	@PrePersist
	public void prePersist(User user) {
		if (user.getJoined() == null) {
			user.setJoined(LocalDateTime.now());
		}
		logger.info(
				"PREPERSIST LOGGER:" + "Creating new User (ID: " + user.getUserId() + ") - Name: " + user.getName());
	}

	// every time a user is updated, the user will be logged as updated with the
	// user's ID and name
	@PreUpdate
	public void preUpdate(User user) {
		logger.info("PREUPDATE LOGGER: Updating User (ID: " + user.getUserId() + ") - Name: " + user.getName());
	}

	// every time a user is deleted, the user will be logged as deleted with the
	// user's ID and name
	@PreRemove
	public void preRemove(User user) {
		logger.info("PREREMOVE LOGGER: Deleting User (ID: " + user.getUserId() + ") - Name: " + user.getName());
	}

	/*
	 * @PostPersist public void postPersist(User user) {
	 * logger.info("POSTPERSIST LOGGER: Created new User (ID: " + user.getUserId() +
	 * ") - Name: " + user.getName()); }
	 * 
	 * @PostUpdate public void postUpdate(User user) {
	 * logger.info("POSTUPDATE LOGGER: Updated User (ID: " + user.getUserId() +
	 * ") - Name: " + user.getName()); }
	 * 
	 * @PostRemove public void postRemove(User user) {
	 * logger.info("POSTREMOVE LOGGER: Deleted User (ID: " + user.getUserId() +
	 * ") - Name: " + user.getName()); }
	 * 
	 * @PostLoad public void postLoad(User user) {
	 * logger.info("POSTLOAD LOGGER: Loaded User (ID: " + user.getUserId() +
	 * ") - Name: " + user.getName()); }
	 */
}
