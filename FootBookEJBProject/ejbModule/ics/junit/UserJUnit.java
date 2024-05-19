package ics.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ics.ejb.Match;
import ics.ejb.User;

class UserJUnit {
	private String expectedUserId;
	private int exptectedAge;
	private String expectedEmail;
	private String expectedName;
	private String expectedGender;
	private Match expectedMatch;
	
	private User user1;
	private User user2;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		expectedUserId = "U1";
		exptectedAge = 21;
		expectedEmail = "alex@gmail.com";
		expectedName = "alex";
		expectedGender = "M";
		expectedMatch = new Match();
		
		user1 = new User(expectedUserId, exptectedAge, expectedEmail, expectedGender, expectedName);
		user2 = new User("U2", 22, "insh@gmail.com", "M", "Insh");
		
		

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testGetUserId() {
		assertEquals("U1", user1.getUserId());
	}

	@Test
	final void testSetUserId() {
		user1.setUserId("U3");
		assertEquals("U3", user1.getUserId());
	}

	@Test
	final void testGetAge() {
		assertEquals(21, user1.getAge());
	}

	@Test
	final void testSetAge() {
		user1.setAge(32);
		assertEquals(32, user1.getAge());
	}
	
	@Test
	final void testGetEmail() {
		assertEquals("alex@gmail.com", user1.getEmail());
	}

	@Test
	final void testSetEmail() {
		user2.setEmail(expectedEmail);
		assertEquals(user1.getEmail(), user2.getEmail());
	}

	@Test
	final void testGetGender() {
		assertEquals("M", user1.getGender());
	}

	@Test
	final void testSetGender() {
		user1.setGender("F");
		assertEquals("F", user1.getGender());
	}

	@Test
	final void testGetName() {
		assertEquals("alex", user1.getName());
	}

	@Test
	final void testSetName() {
		user1.setName("H-bahh");
		assertEquals("H-bahh", user1.getName());
	}

	@Test
	final void testGetMatch() {
		assertEquals(null, user1.getMatch());
		assertEquals(null, user2.getMatch());
	}

	@Test
	final void testSetMatch() {
		user1.setMatch(expectedMatch);
		user2.setMatch(expectedMatch);
		assertEquals(expectedMatch, user1.getMatch());
		assertEquals(expectedMatch, user2.getMatch());
	}

}
