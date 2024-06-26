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

import ics.ejb.Match;

public class MatchAuditor {
	private static final Logger logger = Logger.getLogger(MatchAuditor.class.getName());

	
	//every time a match is created, the createdDate and lastUpdatedDate will be set to the current date and time
	@PrePersist //pre-persist is called before the entity is persisted, meaning before it is saved to the database
	public void prePersist(Match match) {
		match.setCreatedDate(LocalDateTime.now());
		match.setLastUpdatedDate(LocalDateTime.now());
		logger.info("PREPERSIST LOGGER: Creating new Match (ID: " + match.getMatchId() + ") - createdDate: "
				+ match.getCreatedDate());
	}

	//every time a match is updated, the lastUpdatedDate will be set to the current date and time
	@PreUpdate //pre-update is called before the entity is updated, meaning before it is saved to the database
	public void preUpdate(Match match) {
		match.setLastUpdatedDate(LocalDateTime.now());
		logger.info("PREUPDATE LOGGER: Updating Match (ID: " + match.getMatchId() + ") - lastUpdatedDate: "
				+ match.getLastUpdatedDate());
	}

	//every time a match is deleted, the match will be logged as deleted
	@PreRemove //pre-remove is called before the entity is removed, meaning before it is deleted from the database
	public void preRemove(Match match) {
		logger.info("PREREMOVE LOGGER: Deleting Match (ID: " + match.getMatchId());
	}

	/*
	@PostLoad
	public void postLoad(Match match) {
		logger.info("POSTLOAD LOGGER: Loaded Match (ID: " + match.getMatchId());
	}

	@PostPersist
	public void postPersist(Match match) {
		logger.info("POSTPERSIST LOGGER: Created new Match (ID: " + match.getMatchId() + ") - createdDate: "
				+ match.getCreatedDate());
	}

	@PostUpdate
	public void postUpdate(Match match) {
		logger.info("POSTUPDATE LOGGER: Updated Match (ID: " + match.getMatchId() + ") - lastUpdatedDate: "
				+ match.getLastUpdatedDate());
	}
	
	@PostRemove
	public void postRemove(Match match) {
		logger.info("POSTREMOVE LOGGER: Deleted Match (ID: " + match.getMatchId());
	}*/

}
