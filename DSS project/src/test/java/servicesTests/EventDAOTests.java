//package servicesTests;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import entities.Event;
//import jpa.dao.EventDAO;
//import services.EventServiceEJB;
//
//public class EventDAOTests {
//	
//	private EventServiceEJB eventEJB;
//	private String testDate;
//	
//	@Before
//	public void setup(){
//		ArrayList<Event> mockedEvents = new ArrayList<Event>();
//		Date date = new Date("01/01/2013 12:00:00");
//		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:MM:SS");
//		testDate = df.format(date);
//		mockedEvents.add(new Event(date, 1, 1, 1, 1, 1, 1, 1, 1, "neVersion", "imsi", "hier3id", "hier32id",
//				"hier321id"));
//		EventDAO mockedDAO = mock(EventDAO.class);
//		when(mockedDAO.getEventByIMSI(anyString())).thenReturn(mockedEvents);
//		
//		eventEJB = new EventServiceEJB();
//		eventEJB.setEventDao(mockedDAO);
//		
//	}
//
//	@Test
//	public void test() {
//		ArrayList<Event> results = (ArrayList<Event>) eventEJB.getEventByIMSI("imsi");
//		Event resultEvent = results.get(0);
//		assertEquals(results.size(), 1);
//		assertEquals(resultEvent.getDate(), testDate);
//		assertEquals(resultEvent.getEventId(), 1);
//		assertEquals(resultEvent.getFailureClass(), 1);
//		assertEquals(resultEvent.getTac(), 1);
//		assertEquals(resultEvent.getMnc(), 1);
//		assertEquals(resultEvent.getMcc(), 1);
//		assertEquals(resultEvent.getCellId(), 1);
//		assertEquals(resultEvent.getDuration(), 1);
//		assertEquals(resultEvent.getCauseCode(), 1);
//		assertEquals(resultEvent.getNeVersion(), "neVersion");
//		assertEquals(resultEvent.getImsi(), "imsi");
//		assertEquals(resultEvent.getHier3id(), "hier3id");
//		assertEquals(resultEvent.getHier32id(), "hier32id");
//		assertEquals(resultEvent.getHier321id(), "hier321id");
//	}
//
//}
