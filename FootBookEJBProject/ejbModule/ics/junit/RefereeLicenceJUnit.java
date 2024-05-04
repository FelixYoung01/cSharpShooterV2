package ics.junit;

import static org.junit.jupiter.api.Assertions.*;

import ics.ejb.RefereeLicense;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RefereeLicenceJUnit {
	
	private String licenseId;
	
	private RefereeLicense refereeLicence1;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		licenseId = "L1";
		
		refereeLicence1 = new RefereeLicense(licenseId);
		refereeLicence1.setLicenseId(licenseId);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testGetLicenseId() {
		assertEquals(licenseId, refereeLicence1.getLicenseId());
	}

	@Test
	final void testSetLicenseId() {
		String licenseId2 = "L2";
        refereeLicence1.setLicenseId(licenseId2);
        assertEquals(licenseId2, refereeLicence1.getLicenseId());
	}

}
