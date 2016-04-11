package servicesTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import entities.User;
import jpa.dao.UserDAO;
import services.UserServiceEJB;

public class UserDAOTests {

	private UserServiceEJB userEJB;
	private UserServiceEJB userEJB2;

	@Before
	public void setup() {
		ArrayList<User> mockedUsers = new ArrayList<User>();
		mockedUsers.add(
				new User("username","password",2));
		UserDAO mockedDAO = mock(UserDAO.class);
		when(mockedDAO.getUserByType(anyInt())).thenReturn(mockedUsers);

		userEJB = new UserServiceEJB();
		userEJB.setUserDao(mockedDAO);
		
		ArrayList<User> mockedUsers2 = new ArrayList<User>();
		mockedUsers2.add(new User("username1","password1",1));
		mockedUsers2.add(new User("username2","password2",2));
		mockedUsers2.add(new User("username3","password3",3));
		mockedUsers2.add(new User("username4","password4",4));
		UserDAO mockedDAO2 = mock(UserDAO.class);
		when(mockedDAO2.getAllUsers()).thenReturn(mockedUsers2);

		userEJB2 = new UserServiceEJB();
		userEJB2.setUserDao(mockedDAO2);

	}

	@Test
	public void test() {
		ArrayList<User> results = (ArrayList<User>) userEJB.getUserByType(10);
		User resultUser = results.get(0);
		assertTrue(results.size() == 1);
		assertEquals(resultUser.getUsername(), "username");
		assertEquals(resultUser.getPassword(), "password");
		assertEquals(resultUser.getUserType(), 2);
	}

	@Test
	public void test2(){
		ArrayList<User> results = (ArrayList<User>) userEJB2.getAllUsers();
		User resultUser1 = results.get(0);
		User resultUser2 = results.get(1);
		User resultUser3 = results.get(2);
		User resultUser4 = results.get(3);
		assertTrue(results.size() == 4);
		assertEquals(resultUser1.getUsername(), "username1");
		assertEquals(resultUser1.getPassword(), "password1");
		assertEquals(resultUser1.getUserType(), 1);
		assertEquals(resultUser2.getUsername(), "username2");
		assertEquals(resultUser2.getPassword(), "password2");
		assertEquals(resultUser2.getUserType(), 2);
		assertEquals(resultUser3.getUsername(), "username3");
		assertEquals(resultUser3.getPassword(), "password3");
		assertEquals(resultUser3.getUserType(), 3);
		assertEquals(resultUser4.getUsername(), "username4");
		assertEquals(resultUser4.getPassword(), "password4");
		assertEquals(resultUser4.getUserType(), 4);
	}
	
	
}