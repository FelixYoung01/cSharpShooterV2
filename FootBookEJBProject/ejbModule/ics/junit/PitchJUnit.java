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
import ics.ejb.Pitch;

class PitchJUnit {
	
	private String expectedPitchId;
	private String expectedName;
	private Match match;
	
	private Pitch pitch1;
	private Pitch pitch2;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		expectedPitchId = "P1";
		expectedName = "Pitch1";
		
		pitch1 = new Pitch(expectedPitchId, expectedName);
		pitch2 = new Pitch("P2", "Pitch2");
	}

	@AfterEach
	void tearDown() throws Exception {
		pitch1 = null;
		pitch2 = null;
	}

	@Test
	final void testGetPitchId() {
		assertEquals(expectedPitchId, pitch1.getPitchId());
	}

	@Test
	final void testSetPitchId() {
		pitch2.setPitchId("P3");
		assertEquals("P3", pitch2.getPitchId());
	}

	@Test
	final void testGetName() {
		assertEquals(expectedName, pitch1.getName());
	}

	@Test
	final void testSetName() {
		pitch1.setName("Pitch3");
		assertEquals("Pitch3", pitch1.getName());
	}

	@Test
	final void testSetMatches() {
		Set<Match> matches = new HashSet();
		matches.add(match);
		pitch1.setMatches(matches);
		assertEquals(matches, pitch1.getMatches());
		
	}

}
