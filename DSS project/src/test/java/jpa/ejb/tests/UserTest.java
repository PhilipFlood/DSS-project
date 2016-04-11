//package jpa.ejb.tests;
//
//import static org.junit.Assert.*;
//
//import java.io.File;
//import java.util.List;
//
//import javax.inject.Inject;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.arquillian.junit.InSequence;
//import org.jboss.shrinkwrap.api.ArchivePaths;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import entities.User;
//import jpa.dao.IUserDAO;
//import jpa.dao.UserDAO;
//
//
//@RunWith(Arquillian.class)
//public class UserTest {
//	
//	@Inject
//	IUserDAO userDAO;
//	
//	@Deployment
//	public static JavaArchive createDeployment() {
//		return ShrinkWrap.create(JavaArchive.class, "usertest.jar")
//		.addClasses(User.class,IUserDAO.class,UserDAO.class)
//		.addAsManifestResource(EmptyAsset.INSTANCE,ArchivePaths.create("beans.xml"))
//		.addAsManifestResource(new File("src/test/resources/test-persistence.xml"),"persistence.xml");
//		}
//	
//	@Test @InSequence(1)
//	public void addUser() {
//		User user1 = new User("kevin","123",0);
//		User user2 = new User("abdul","123",1);
//		User user3 = new User("cian","123",2);
//		User user4 = new User("philip","123",3);
//		
//		userDAO.addUser(user1);
//		userDAO.addUser(user2);
//		userDAO.addUser(user3);
//		userDAO.addUser(user4);
//		
//		
//		
//		assertEquals(userDAO.getAllUsers().size(),4);
//
//		
//	}
//	
//	@Test @InSequence(2)
//	public void getUserByType(){
//		User user4 = new User("philip","123",3);
//		
//		List<User> philip = (List<User>) userDAO.getUserByType(3);
//		
//		assertEquals(philip.get(0).getUsername(),user4.getUsername());
//		assertEquals(philip.get(0).getPassword(),user4.getPassword());
//		assertEquals(philip.get(0).getUserType(),user4.getUserType());
//	}
//
//	@Test @InSequence(3)
//	public void removeUser(){
//		User user4 = new User("philip","123",3);
//		
//		userDAO.deleteUser(user4);
//		
//		List<User>users = (List<User>) userDAO.getAllUsers();
//		
//		assertFalse(users.contains(user4));
//		assertEquals(userDAO.getAllUsers().size(),3);
//	}
//	
//	@Test @InSequence(4)
//	public void getUserByName(){
//		User user2 = new User("abdul","123",1);
//		User abdul = userDAO.getUserByName("abdul");
//		
//		assertEquals(abdul.getPassword(),user2.getPassword());
//		assertEquals(abdul.getUserType(),user2.getUserType());
//		assertEquals(abdul.getUsername(),user2.getUsername());
//		
//		
//	}
//}
