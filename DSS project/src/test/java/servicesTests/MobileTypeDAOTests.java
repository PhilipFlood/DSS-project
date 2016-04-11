package servicesTests;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import jpa.dao.MobileTypeDAO;

import org.junit.Before;
import org.junit.Test;

import services.MobileTypeServiceEJB;
import entities.MobileType;

public class MobileTypeDAOTests {

	private MobileTypeServiceEJB mobileTypeEJB;
	
	@Before
	public void setup(){
		ArrayList<MobileType> mockedEvents = new ArrayList<MobileType>();
		mockedEvents.add(new MobileType(1, "marketingName", "manufacturer",
				"accessCompatibility", "ueType", "os",
				"inputType"));
		MobileTypeDAO mockedDAO = mock(MobileTypeDAO.class);
		when(mockedDAO.searchTac(anyInt())).thenReturn(mockedEvents);
		
		mobileTypeEJB = new MobileTypeServiceEJB();
		mobileTypeEJB.setMobileTypeDao(mockedDAO);
		
	}

	@Test
	public void test() {
		List<MobileType> results = mobileTypeEJB.searchTac(5);
		MobileType resultEvent = results.get(0);
		assertTrue(results.size() == 1);
		assertEquals(resultEvent.getTac(), 1);
		assertEquals(resultEvent.getMarketingName(), "marketingName");
		assertEquals(resultEvent.getManufacturer(), "manufacturer");
		assertEquals(resultEvent.getAccessCompatibility(), "accessCompatibility");
		assertEquals(resultEvent.getUeType(), "ueType");
		assertEquals(resultEvent.getOs(), "os");
		assertEquals(resultEvent.getInputType(), "inputType");
	}

}
