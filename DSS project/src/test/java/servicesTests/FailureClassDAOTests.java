package servicesTests;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import jpa.dao.FailureClassDAO;

import org.junit.Before;
import org.junit.Test;

import services.FailureClassServiceEJB;
import entities.FailureClass;

public class FailureClassDAOTests {
	
	private FailureClassServiceEJB failureClassEJB;
	
	@Before
	public void setup(){
		ArrayList<FailureClass> mockedEvents = new ArrayList<FailureClass>();
		mockedEvents.add(new FailureClass(5, "description"));
		FailureClassDAO mockedDAO = mock(FailureClassDAO.class);
		when(mockedDAO.searchFailureClass(anyInt())).thenReturn(mockedEvents);
		
		failureClassEJB = new FailureClassServiceEJB();
		failureClassEJB.setFailureClassDao(mockedDAO);
		
	}

	@Test
	public void test() {
		List<FailureClass> results = failureClassEJB.searchFailureClass(7);
		FailureClass resultEvent = results.get(0);
		assertTrue(results.size() == 1);
		assertEquals(resultEvent.getFailureCode(), 5);
		assertEquals(resultEvent.getDescription(), "description");
	}

}
