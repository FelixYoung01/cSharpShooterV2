package ics.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ics.ejb.Match;
import ics.ejb.Referee;

class RefereeJUnit {
	
	private String refereeId;
	private String refereeName;
	
	private Match match1;
	private Match match2;
	private Match match3;
	
	private Referee referee1;
	private Referee referee2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		refereeId = "R1";
		refereeName = "haren";

		referee1 = new Referee(refereeId, refereeName);
		referee2 = new Referee("R2", "berra");
		
		match1 = new Match();
		match2 = new Match();
		match3 = new Match();
		
		Set<Match> matches1 = new HashSet<>();
		matches1.add(match1);
		referee1.setMatches(matches1);
		
		Set<Match> matches2 = new HashSet<>();
		matches2.add(match2);
		referee2.setMatches(matches2);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testGetRefereeId() {
		
	}

	@Test
	final void testSetRefereeId() {
        assertEquals("R1", referee1.getRefereeId());
	}

	@Test
	final void testGetRefereeName() {
		assertEquals("haren", referee1.getRefereeName());
	}

	@Test
	final void testSetRefereeName() {
		referee1.setRefereeName("haren igen");
		assertEquals("haren igen", referee1.getRefereeName());
	}

	@Test
	final void testGetMatches() {
		assertTrue(referee1.getMatches().contains(match1));
	}

	@Test
	final void testSetMatches() {
		Set<Match> matches = referee1.getMatches();
		matches.add(match3);
		referee1.setMatches(matches);
		assertEquals(matches, referee1.getMatches());
	}

}
