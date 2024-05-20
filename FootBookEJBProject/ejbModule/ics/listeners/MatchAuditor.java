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

	@PrePersist
	public void prePersist(Match match) {
		match.setCreatedDate(LocalDateTime.now());
		match.setLastUpdatedDate(LocalDateTime.now());
		logger.info("PREPERSIST LOGGER: Creating new Match (ID: " + match.getMatchId() + ") - createdDate: "
				+ match.getCreatedDate());
	}

	@PreUpdate
	public void preUpdate(Match match) {
		match.setLastUpdatedDate(LocalDateTime.now());
		logger.info("PREUPDATE LOGGER: Updating Match (ID: " + match.getMatchId() + ") - lastUpdatedDate: "
				+ match.getLastUpdatedDate());
	}

	@PreRemove
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
