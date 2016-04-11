package servicesTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import jpa.dao.NetworkDAO;

import org.junit.Before;
import org.junit.Test;

import services.NetworkServiceEJB;
import entities.Network;

public class NetworkDAOTests {
	
	private NetworkServiceEJB networkEJB;
	
	@Before
	public void setup(){
		ArrayList<Network> mockedEvents = new ArrayList<Network>();
		mockedEvents.add(new Network(1, 2, "country", "operator"));
		NetworkDAO mockedDAO = mock(NetworkDAO.class);
		when(mockedDAO.searchMCCAndMNC(anyInt(), anyInt())).thenReturn(mockedEvents);
		
		networkEJB = new NetworkServiceEJB();
		networkEJB.setNetworkDao(mockedDAO);
		
	}

	@Test
	public void test() {
		List<Network> results = networkEJB.searchMCCAndMNC(5, 5);
		Network resultNetwork = results.get(0);
		assertTrue(results.size() == 1);
		assertEquals(resultNetwork.getMcc(), 1);
		assertEquals(resultNetwork.getMnc(), 2);
		assertEquals(resultNetwork.getCountry(), "country");
		assertEquals(resultNetwork.getOperator(), "operator");
	}

}
