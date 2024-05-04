package ics.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ics.ejb.Match;
import ics.ejb.Pitch;

class MatchJUnit {
	private String matchId;
	private Pitch pitch;
	private Pitch pitch2;
	private LocalDate date;
	private LocalDate date2;
	private LocalTime time2;
	private LocalTime time;
	
	private Match match1;
	private Match match2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		matchId = "M1";
		pitch = new Pitch("P1", "Pitch1");
		pitch2 = new Pitch("P2", "Pitch2");
		date = LocalDate.of(2021, 12, 31);
		date2 = LocalDate.of(2022, 12, 31);
		time = LocalTime.of(13, 00);
		time2 = LocalTime.of(14, 00);
		
		match1 = new Match(matchId, pitch, date, time);
		match2 = new Match("M2", pitch2, date2, time2);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		match1 = null;
		match2 = null;
	}

	@Test
	final void testGetMatchId() {
		assertEquals("M1", match1.getMatchId());
	}

	@Test
	final void testSetMatchId() {
		String newMatchId = "M3";
		match1.setMatchId(newMatchId);
		assertEquals(newMatchId, match1.getMatchId());
	}

	@Test
	final void testGetPitch() {
		assertNotNull(match1);
		assertEquals(pitch, match1.getPitch());
	}

	@Test
	final void testSetPitch() {
		match1.setPitch(pitch2);
		assertEquals(pitch2, match1.getPitch());
	}

	@Test
	final void testGetDate() {
		assertEquals(date, match1.getDate());
	}

	@Test
	final void testSetDate() {
		match1.setDate(date2);
		assertEquals(date2, match1.getDate());
	}

	@Test
	final void testGetTime() {
		assertEquals(time, match1.getTime());
	}

	@Test
	final void testSetTime() {
		match1.setTime(time2);
        assertEquals(time2, match1.getTime());
    }
	}


