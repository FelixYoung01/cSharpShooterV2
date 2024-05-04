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
	private String userId;
	private int age;
	private String email;
	private String name;
	private String gender;
	private Match match;
	
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
		
		userId = "U1";
		age = 21;
		email = "alex@gmail.com";
		name = "alex";
		gender = "M";
		match = new Match();
		
		User user1 = new User(userId, age, email, gender, name);
		//User user2 = new User();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testGetUserId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetUserId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetAge() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetAge() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetEmail() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetEmail() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetGender() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetGender() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetName() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetName() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetMatch() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetMatch() {
		fail("Not yet implemented"); // TODO
	}

}
