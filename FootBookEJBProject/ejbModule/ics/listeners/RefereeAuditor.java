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

import org.hibernate.validator.internal.util.logging.LoggerFactory;

import ics.ejb.Referee;

public class RefereeAuditor {
	private static final Logger logger = Logger.getLogger(RefereeAuditor.class.getName());

	// every time a referee is created, the createdDate and lastUpdatedDate will be set to the current date and time
	@PrePersist // pre-persist is called before the entity is persisted, meaning before it is saved to the database
	public void prePersist(Referee referee) {
		logger.info("PREPERSIST LOGGER: Creating new Referee (ID: " + referee.getRefereeId() + ") - Name: "
				+ referee.getRefereeName());
	}
	
	// every time a referee is updated, the lastUpdatedDate will be set to the current date and time
	@PreUpdate // pre-update is called before the entity is updated, meaning before it is saved to the database
	public void preUpdate(Referee referee) {
		logger.info("PREUPDATE LOGGER: Updating Referee (ID: " + referee.getRefereeId() + ") - Name: "
				+ referee.getRefereeName());
	}
	// every time a referee is deleted, the referee will be logged as deleted
	@PreRemove // pre-remove is called before the entity is removed, meaning before it is deleted from the database
	public void preRemove(Referee referee) {
		logger.info("PREREMOVE LOGGER: Deleting Referee (ID: " + referee.getRefereeId() + ") - Name: "
				+ referee.getRefereeName());
	}

	
	/*@PostLoad
	public void postLoad(Referee referee) {
		logger.info("POSTLOAD LOGGER: Loaded Referee (ID: " + referee.getRefereeId() + ") - Name: "
				+ referee.getRefereeName());
	}

	@PostPersist
	public void postPersist(Referee referee) {
		logger.info("POSTPERSIST LOGGER: Created new Referee (ID: " + referee.getRefereeId() + ") - Name: "
				+ referee.getRefereeName());
	}

	@PostUpdate
	public void postUpdate(Referee referee) {
		logger.info("POSTUPDATE LOGGER: Updated Referee (ID: " + referee.getRefereeId() + ") - Name: "
				+ referee.getRefereeName());
	}

	@PostRemove
	public void postRemove(Referee referee) {
		logger.info("POSTREMOVE LOGGER: Deleted Referee (ID: " + referee.getRefereeId() + ") - Name: "
				+ referee.getRefereeName());
	}*/

}
