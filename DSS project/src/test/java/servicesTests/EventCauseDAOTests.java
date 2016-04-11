package servicesTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import jpa.dao.EventCauseDAO;

import org.junit.Before;
import org.junit.Test;

import entities.EventCause;
import services.EventCauseServiceEJB;

public class EventCauseDAOTests {

	private EventCauseServiceEJB eventCauseEJB;
	
	@Before
	public void setup(){
		ArrayList<EventCause> mockedEvents = new ArrayList<EventCause>();
		mockedEvents.add(new EventCause(1, 2, "description"));
		EventCauseDAO mockedDAO = mock(EventCauseDAO.class);
		when(mockedDAO.searchCausedCodeAndEventID(anyInt(), anyInt())).thenReturn(mockedEvents);
		
		eventCauseEJB = new EventCauseServiceEJB();
		eventCauseEJB.setEventCauseDao(mockedDAO);
		
	}

	@Test
	public void test() {
		List<EventCause> results = eventCauseEJB.searchCausedCodeAndEventID(5, 5);
		EventCause resultEvent = results.get(0);
		assertTrue(results.size() == 1);
		assertEquals(resultEvent.getCauseCode(), 1);
		assertEquals(resultEvent.getEventId(), 2);
		assertEquals(resultEvent.getDescription(), "description");
	}

}
