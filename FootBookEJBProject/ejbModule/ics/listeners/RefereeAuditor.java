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

	@PrePersist
	public void prePersist(Referee referee) {
		logger.info("PREPERSIST LOGGER: Creating new Referee (ID: " + referee.getRefereeId() + ") - Name: "
				+ referee.getRefereeName());
	}

	@PreUpdate
	public void preUpdate(Referee referee) {
		logger.info("PREUPDATE LOGGER: Updating Referee (ID: " + referee.getRefereeId() + ") - Name: "
				+ referee.getRefereeName());
	}

	@PreRemove
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
